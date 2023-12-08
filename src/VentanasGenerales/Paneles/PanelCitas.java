package VentanasGenerales.Paneles;

import DAO.CitaCRUD;
import Entidades.Cita;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Vector;

public class PanelCitas extends JScrollPane {

    private JTable tblCitas;
    private DefaultTableModel tableModel;
    private CitaCRUD crudCita = new CitaCRUD();
    private LinkedHashSet<Cita> listaCitas = crudCita.obtenerRegistro();

    private Cita citaSeleccionada = null;

    public PanelCitas() {
        // Inicializar la tabla y su modelo
        tableModel = new DefaultTableModel(
                new Object[]{"", "ID Cita", "Fecha", "Hora", "Nombre Paciente", "Apellidos Paciente", "Nombre Doctor", "Apellidos Doctor", "Motivo", "Estado"}, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Boolean.class : Object.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }
        };

        tblCitas = new JTable(tableModel);
        tblCitas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Agregar ActionListener al checkbox en la columna 0 (primer columna)
        TableColumn checkboxColumn = tblCitas.getColumnModel().getColumn(0);
        checkboxColumn.setCellEditor(tblCitas.getDefaultEditor(Boolean.class));
        checkboxColumn.setCellRenderer(tblCitas.getDefaultRenderer(Boolean.class));
        JCheckBox checkBox = new JCheckBox();
        checkBox.setHorizontalAlignment(JCheckBox.CENTER);
        checkboxColumn.setCellEditor(new DefaultCellEditor(checkBox));

        // Agregar ActionListener al checkbox
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblCitas.getSelectedRow();
                if (selectedRow >= 0) {
                    boolean isChecked = (Boolean) tblCitas.getValueAt(selectedRow, 0);

                    // Si la casilla ya estaba seleccionada, deseleccionarla y poner citaSeleccionada a null
                    if (isChecked) {
                        tblCitas.setValueAt(false, selectedRow, 0);
                        citaSeleccionada = null;
                    } else {
                        // Desmarcar todas las casillas antes de marcar la seleccionada
                        for (int i = 0; i < tableModel.getRowCount(); i++) {
                            if (i != selectedRow) {
                                tableModel.setValueAt(false, i, 0);
                            }
                        }

                        // Cita seleccionada
                        citaSeleccionada = getCitaFromRow(selectedRow, listaCitas);
                    }
                }
            }
        });

        // Establecer el modelo de ordenación de la tabla para organizar por fecha
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        tblCitas.setRowSorter(sorter);
        sorter.setComparator(2, Comparator.comparing(o -> LocalDate.parse((CharSequence) o, DateTimeFormatter.ofPattern("yyyy-MM-dd"))));

        // Establecer la tabla como la vista del JScrollPane
        setViewportView(tblCitas);

        // Llenar la tabla inicialmente
        llenarTabla();
    }

    private Cita getCitaFromRow(int row, LinkedHashSet<Cita> citas) {
        Object idValue = tblCitas.getValueAt(row, 1);
        int selectedId = Integer.parseInt(idValue.toString());

        // Buscar la cita en la colección
        for (Cita cita : citas) {
            if (cita.getId() == selectedId) {
                return cita;
            }
        }
        return null; // Devolver null si no se encuentra la cita (manejar según necesidades)
    }

    public Cita getCitaSeleccionada() {
        return citaSeleccionada;
    }

    public void setCitaSeleccionada(Cita citaSeleccionada) {
        this.citaSeleccionada = citaSeleccionada;
    }

    public void llenarTabla() {
        // Limpiar filas existentes
        tableModel.setRowCount(0);

        listaCitas = crudCita.obtenerRegistro();

        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        for (Cita cita : listaCitas) {
            // Mostrar solo citas después de la fecha actual
            if (cita.getFecha().isAfter(fechaActual)) {
                Vector<Object> row = new Vector<>();
                row.add(false);
                row.add(cita.getId());
                row.add(cita.getFecha());
                row.add(cita.getFormattedHora());
                row.add(cita.getPaciente().getNombre());
                row.add(cita.getPaciente().getApellidoP() + " " + cita.getPaciente().getApellidoM());
                row.add(cita.getDoctor().getNombre());
                row.add(cita.getDoctor().getApellidoP() + " " + cita.getDoctor().getApellidoM());
                row.add(cita.getMotivo());
                row.add(cita.getEstado());
                tableModel.addRow(row);
            }
        }
    }
}
