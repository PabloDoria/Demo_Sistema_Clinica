import DAO.DoctorCRUD;
import Entidades.Doctor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;
import java.util.Vector;

public class PaginaPrincipal extends JFrame {

    private JButton buscarButton;
    private JTextField textField1;
    private JButton agregarButton;
    private JButton modificarButton;
    private JButton quitarButton;
    private JTabbedPane tabbedPane1;
    private JTable table1;
    private JPanel MiPanel;
    private JPanel PanelTabla = new JPanel();

    public PaginaPrincipal() {

        DefaultTableModel tableModel = new DefaultTableModel(
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

        table1.setModel(tableModel);
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table1.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = table1.getSelectedRow();
            if (selectedRow >= 0) {
                Object idValue = table1.getValueAt(selectedRow, 1);
                System.out.println("ID seleccionado: " + idValue);
            }
        });

        DoctorCRUD crud = new DoctorCRUD();
        LinkedHashSet<Doctor> doctores = crud.obtenerRegistro();
        llenarTabla(tableModel, doctores);

        // Agregar ActionListener al checkbox en la columna 0 (primer columna)
        TableColumn checkboxColumn = table1.getColumnModel().getColumn(0);
        checkboxColumn.setCellEditor(table1.getDefaultEditor(Boolean.class));
        checkboxColumn.setCellRenderer(table1.getDefaultRenderer(Boolean.class));
        JCheckBox checkBox = new JCheckBox();
        checkBox.setHorizontalAlignment(JCheckBox.CENTER);
        checkboxColumn.setCellEditor(new DefaultCellEditor(checkBox));

        // Agregar ActionListener al checkbox
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow();
                if (selectedRow >= 0) {
                    Boolean isChecked = (Boolean) table1.getValueAt(selectedRow, 0);

                    // Verificar si la casilla se ha marcado
                    if (!isChecked) {
                        // Doctor seleccionado
                        Doctor selectedDoctor = getDoctorFromRow(selectedRow, doctores);

                        System.out.println("Doctor seleccionado: " + selectedDoctor.getNombre());
                    } else {
                        // Aquí puedes manejar la lógica cuando la casilla se desmarca
                        // Por ejemplo, cerrar la ventana de edición si ya está abierta.
                    }
                }
            }
        });



        // Agregar ActionListener al botón "Modificar"
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verificar si al menos una casilla está seleccionada
                boolean alMenosUnaSeleccionada = false;
                for (int i = 0; i < table1.getRowCount(); i++) {
                    if ((Boolean) table1.getValueAt(i, 0)) {
                        alMenosUnaSeleccionada = true;
                        break;
                    }
                }

                if (alMenosUnaSeleccionada) {
                    int selectedRow = table1.getSelectedRow();
                    // Llamar a la función de modificación en otra ventana
                    System.out.println("Modificando doctores seleccionados...");
                    Doctor selectedDoctor = getDoctorFromRow(selectedRow, doctores);
                    System.out.println(selectedDoctor.getNombre());
                    EditarDoctor edicion = new EditarDoctor();
                    edicion.setDoctor(selectedDoctor);
                    edicion.setContentPane(edicion.getPanelDoctor());
                    edicion.setSize(1000, 200);
                    edicion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    edicion.setVisible(true);

                    vaciarTabla(tableModel);

                    DoctorCRUD crudo = new DoctorCRUD();
                    LinkedHashSet<Doctor> doctoress = crudo.obtenerRegistro();

                    llenarTabla(tableModel, doctoress);

                } else {
                    JOptionPane.showMessageDialog(PaginaPrincipal.this, "Seleccione al menos un doctor para modificar.",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private Doctor getDoctorFromRow(int row, LinkedHashSet<Doctor> doctores) {
        // Obtener el ID del doctor seleccionado
        Object idValue = table1.getValueAt(row, 1);
        int selectedId = Integer.parseInt(idValue.toString());

        // Buscar el doctor en la colección
        for (Doctor doctor : doctores) {
            if (doctor.getId() == selectedId) {
                return doctor;
            }
        }
        return null; // Devolver null si no se encuentra el doctor (deberías manejar este caso según tus necesidades)
    }

    private void llenarTabla(DefaultTableModel tableModel, LinkedHashSet<Doctor> doctores) {
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

    private void vaciarTabla(DefaultTableModel tableModel) {
        int rowCount = tableModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }
    }

    public static void main(String[] args) {
        PaginaPrincipal v = new PaginaPrincipal();
        v.setContentPane(v.MiPanel);
        v.setSize(1920, 1080);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.setVisible(true);
    }
}
