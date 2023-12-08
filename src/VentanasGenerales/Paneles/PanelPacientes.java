package VentanasGenerales.Paneles;

import DAO.PacienteCRUD;
import Entidades.Paciente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;
import java.util.Vector;

public class PanelPacientes extends JScrollPane {

    private JTable tblPacientes;
    private DefaultTableModel tableModel;
    private PacienteCRUD crud = new PacienteCRUD(); // Asume que tienes una clase PacienteCRUD similar a DoctorCRUD
    public boolean casillaSeleccionada = false;

    public Paciente getPacienteSeleccionado() {
        return pacienteSeleccionado;
    }

    public void setPacienteSeleccionado(Paciente pacienteSeleccionado) {
        this.pacienteSeleccionado = pacienteSeleccionado;
    }

    private Paciente pacienteSeleccionado = null;
    private LinkedHashSet<Paciente> pacientes = crud.obtenerRegistro();

    public PanelPacientes() {
        // Inicializar la tabla y su modelo
        tableModel = new DefaultTableModel(
                new Object[]{"", "ID", "Nombre", "Apellido materno", "Apellido paterno", "Peso", "Estatura", "Tipo de sangre", "Fecha de nacimiento", "Dirección", "Doctor Cabecera", "Correo Electrónico", "Celular"}, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Boolean.class : Object.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }
        };

        tblPacientes = new JTable(tableModel);
        tblPacientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tblPacientes.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = tblPacientes.getSelectedRow();
            if (selectedRow >= 0) {
                Object idValue = tblPacientes.getValueAt(selectedRow, 1);
                System.out.println("ID seleccionadoooo: " + idValue);
            }
        });

        // Agregar ActionListener al checkbox en la columna 0 (primer columna)
        TableColumn checkboxColumn = tblPacientes.getColumnModel().getColumn(0);
        checkboxColumn.setCellEditor(tblPacientes.getDefaultEditor(Boolean.class));
        checkboxColumn.setCellRenderer(tblPacientes.getDefaultRenderer(Boolean.class));
        JCheckBox checkBox = new JCheckBox();
        checkBox.setHorizontalAlignment(JCheckBox.CENTER);
        checkboxColumn.setCellEditor(new DefaultCellEditor(checkBox));

        // Agregar ActionListener al checkbox
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblPacientes.getSelectedRow();
                if (selectedRow >= 0) {
                    boolean isChecked = (Boolean) tblPacientes.getValueAt(selectedRow, 0);

                    // Si la casilla ya estaba seleccionada, deseleccionarla y poner pacienteSeleccionado a null
                    if (isChecked) {
                        tblPacientes.setValueAt(false, selectedRow, 0);
                        pacienteSeleccionado = null;
                        casillaSeleccionada = false;
                    } else {
                        // Desmarcar todas las casillas antes de marcar la seleccionada
                        for (int i = 0; i < tableModel.getRowCount(); i++) {
                            if (i != selectedRow) {
                                tableModel.setValueAt(false, i, 0);
                            }
                        }

                        // Paciente seleccionado
                        pacienteSeleccionado = getPacienteFromRow(selectedRow, pacientes);
                        casillaSeleccionada = true;
                    }
                }
            }
        });

        // Establecer la tabla como la vista del JScrollPane
        setViewportView(tblPacientes);

        // Llenar la tabla inicialmente
        llenarTabla();
    }

    // Métodos necesarios para trabajar con la tabla
    private Paciente getPacienteFromRow(int row, LinkedHashSet<Paciente> pacientes) {
        Object idValue = tblPacientes.getValueAt(row, 1);
        int selectedId = Integer.parseInt(idValue.toString());

        // Buscar el paciente en la colección
        for (Paciente paciente : pacientes) {
            if (paciente.getId() == selectedId) {
                return paciente;
            }
        }
        return null; // Devolver null si no se encuentra el paciente (manejar según necesidades)
    }

    public JTable getTblPacientes() {
        return tblPacientes;
    }

    public void llenarTabla() {
        pacientes = crud.obtenerRegistro();

        tableModel.setRowCount(0); // Limpiar filas existentes

        for (Paciente paciente : pacientes) {
            Vector<Object> row = new Vector<>();
            row.add(false);
            row.add(paciente.getId());
            row.add(paciente.getNombre());
            row.add(paciente.getApellidoP());
            row.add(paciente.getApellidoM());
            row.add(paciente.getPeso());
            row.add(paciente.getEstatura());
            row.add(paciente.getTipo_sangre());
            row.add(paciente.getFecha_nacimiento());
            row.add(paciente.getDireccion());
            row.add(paciente.getDoctorCabecera());
            row.add(paciente.getCorreoElectronico());
            row.add(paciente.getCelular());
            tableModel.addRow(row);
        }
    }
}
