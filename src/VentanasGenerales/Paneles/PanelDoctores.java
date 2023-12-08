package VentanasGenerales.Paneles;

import DAO.DoctorCRUD;
import Entidades.Doctor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;
import java.util.Vector;

public class PanelDoctores extends JScrollPane {

    private JTable tblDoctores;
    private DefaultTableModel tableModel;
    private DoctorCRUD crud = new DoctorCRUD();
    public boolean casillaSeleccionada = false;

    public Doctor getDoctorSeleccionado() {
        return doctorSeleccionado;
    }

    public void setDoctorSeleccionado(Doctor doctorSeleccionado) {
        this.doctorSeleccionado = doctorSeleccionado;
    }

    private Doctor doctorSeleccionado = null;

    private
    LinkedHashSet<Doctor> doctores = crud.obtenerRegistro();

    public PanelDoctores() {
        // Inicializar la tabla y su modelo
        tableModel = new DefaultTableModel(
                new Object[]{"", "ID", "Nombre", "Apellido materno", "Apellido paterno", "Especialidad", "Turno", "Dirección", "Celular"}, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Boolean.class : Object.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }
        };

        tblDoctores = new JTable(tableModel);
        tblDoctores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tblDoctores.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = tblDoctores.getSelectedRow();
            if (selectedRow >= 0) {
                Object idValue = tblDoctores.getValueAt(selectedRow, 1);
                System.out.println("ID seleccionadoooo: " + idValue);
            }
        });

        // Agregar ActionListener al checkbox en la columna 0 (primer columna)
        TableColumn checkboxColumn = tblDoctores.getColumnModel().getColumn(0);
        checkboxColumn.setCellEditor(tblDoctores.getDefaultEditor(Boolean.class));
        checkboxColumn.setCellRenderer(tblDoctores.getDefaultRenderer(Boolean.class));
        JCheckBox checkBox = new JCheckBox();
        checkBox.setHorizontalAlignment(JCheckBox.CENTER);
        checkboxColumn.setCellEditor(new DefaultCellEditor(checkBox));

        // Agregar ActionListener al checkbox
        // Agregar ActionListener al checkbox
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblDoctores.getSelectedRow();
                if (selectedRow >= 0) {
                    boolean isChecked = (Boolean) tblDoctores.getValueAt(selectedRow, 0);

                    // Si la casilla ya estaba seleccionada, deseleccionarla y poner doctorSeleccionado a null
                    if (isChecked) {
                        tblDoctores.setValueAt(false, selectedRow, 0);
                        doctorSeleccionado = null;
                        casillaSeleccionada = false;
                    } else {
                        // Desmarcar todas las casillas antes de marcar la seleccionada
                        for (int i = 0; i < tableModel.getRowCount(); i++) {
                            if (i != selectedRow) {
                                tableModel.setValueAt(false, i, 0);
                            }
                        }

                        // Doctor seleccionado
                        doctorSeleccionado = getDoctorFromRow(selectedRow, doctores);
                        casillaSeleccionada = true;
                    }
                }
            }
        });

        // Establecer la tabla como la vista del JScrollPane
        setViewportView(tblDoctores);

        // Llenar la tabla inicialmente
        llenarTabla();
    }

    // Métodos necesarios para trabajar con la tabla
    private Doctor getDoctorFromRow(int row, LinkedHashSet<Doctor> doctores) {
        Object idValue = tblDoctores.getValueAt(row, 1);
        int selectedId = Integer.parseInt(idValue.toString());

        // Buscar el doctor en la colección
        for (Doctor doctor : doctores) {
            if (doctor.getId() == selectedId) {
                return doctor;
            }
        }
        return null; // Devolver null si no se encuentra el doctor (deberías manejar este caso según tus necesidades)
    }

    public JTable getTblDoctores() {
        return tblDoctores;
    }

    public void llenarTabla() {

        doctores = crud.obtenerRegistro();

        tableModel.setRowCount(0); // Limpiar filas existentes

        for (Doctor doctor : doctores) {
            Vector<Object> row = new Vector<>();
            row.add(false);
            row.add(doctor.getId());
            row.add(doctor.getNombre());
            row.add(doctor.getApellidoP());
            row.add(doctor.getApellidoM());
            row.add(doctor.getEspecialidad());
            row.add(doctor.getTurno());
            row.add(doctor.getDireccion());
            row.add(doctor.getCelular());
            tableModel.addRow(row);
        }
    }
}
