package VentanasGenerales;

import DAO.DoctorCRUD;
import Entidades.Doctor;
import VentanasDoctor.AgregarDoctor;
import VentanasDoctor.EditarDoctor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;
import java.util.Vector;

public class PaginaPrincipal extends JFrame {

    private JButton buscarButton;
    private JTextField txtBuscar;
    private JButton agregarButton;
    private JButton modificarButton;
    private JButton quitarButton;
    private JTabbedPane tbdPaneGeneral;
    private JTable tblDoctores;
    private JPanel MiPanel;
    private JTable tblCitas;
    private JButton BuscarFechaButton;
    private JComboBox cmbDia;
    private JComboBox cmbMes;
    private JComboBox cmbAno;
    private JScrollPane sclPaneCitas;
    private JScrollPane sclPaneDoctores;
    private JPanel PanelTabla = new JPanel();

    DoctorCRUD crud = new DoctorCRUD();
    LinkedHashSet<Doctor> doctores = crud.obtenerRegistro();

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

        tblDoctores.setModel(tableModel);
        tblDoctores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tblDoctores.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = tblDoctores.getSelectedRow();
            if (selectedRow >= 0) {
                Object idValue = tblDoctores.getValueAt(selectedRow, 1);
                System.out.println("ID seleccionado: " + idValue);
            }
        });

        llenarTabla(tableModel, doctores);

        // Agregar ActionListener al checkbox en la columna 0 (primer columna)
        TableColumn checkboxColumn = tblDoctores.getColumnModel().getColumn(0);
        checkboxColumn.setCellEditor(tblDoctores.getDefaultEditor(Boolean.class));
        checkboxColumn.setCellRenderer(tblDoctores.getDefaultRenderer(Boolean.class));
        JCheckBox checkBox = new JCheckBox();
        checkBox.setHorizontalAlignment(JCheckBox.CENTER);
        checkboxColumn.setCellEditor(new DefaultCellEditor(checkBox));

        // Agregar ActionListener al checkbox
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblDoctores.getSelectedRow();
                if (selectedRow >= 0) {
                    Boolean isChecked = (Boolean) tblDoctores.getValueAt(selectedRow, 0);

                    // Verificar si la casilla se ha marcado
                    if (!isChecked) {
                        // Doctor seleccionado
                        Doctor selectedDoctor = getDoctorFromRow(selectedRow, doctores);

                        System.out.println("Doctor seleccionado: " + selectedDoctor.getNombre());
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
                for (int i = 0; i < tblDoctores.getRowCount(); i++) {
                    if ((Boolean) tblDoctores.getValueAt(i, 0)) {
                        alMenosUnaSeleccionada = true;
                        break;
                    }
                }

                if (alMenosUnaSeleccionada) {
                    int selectedRow = tblDoctores.getSelectedRow();
                    // Llamar a la función de modificación en otra ventana
                    System.out.println("Modificando doctores seleccionados...");
                    Doctor selectedDoctor = getDoctorFromRow(selectedRow, doctores);
                    System.out.println(selectedDoctor.getNombre());

                    abrirDialogoEditarDoctor(selectedDoctor);
                    vaciarTabla(tableModel);
                    actualizarDoctores();
                    llenarTabla(tableModel, doctores);

                } else {
                    JOptionPane.showMessageDialog(PaginaPrincipal.this, "Seleccione al menos un doctor para modificar.",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirDialogoAgregarDoctor();
                actualizarDoctores();
                vaciarTabla(tableModel);
                llenarTabla(tableModel, doctores);
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Doctor resultado = crud.buscarDoctorId(Integer.parseInt(txtBuscar.getText()));
                if(resultado==null){
                    JOptionPane.showMessageDialog(PaginaPrincipal.this, "El ID que busca no esta relacionado con ningún doctor.",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                } else {
                    abrirDialogoEditarDoctor(resultado);
                }
            }
        });
        quitarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean alMenosUnaSeleccionada = false;
                for (int i = 0; i < tblDoctores.getRowCount(); i++) {
                    if ((Boolean) tblDoctores.getValueAt(i, 0)) {
                        alMenosUnaSeleccionada = true;
                        break;
                    }
                }

                if (alMenosUnaSeleccionada) {
                    int selectedRow = tblDoctores.getSelectedRow();
                    // Llamar a la función de modificación en otra ventana
                    System.out.println("Modificando doctores seleccionados...");
                    Doctor selectedDoctor = getDoctorFromRow(selectedRow, doctores);
                    System.out.println(selectedDoctor.getNombre());

                    doctores.remove(selectedDoctor);
                    crud.insertarDoctor(doctores);
                    vaciarTabla(tableModel);
                    llenarTabla(tableModel, doctores);

                } else {
                    JOptionPane.showMessageDialog(PaginaPrincipal.this, "Seleccione al menos un doctor para modificar.",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private Doctor getDoctorFromRow(int row, LinkedHashSet<Doctor> doctores) {
        // Obtener el ID del doctor seleccionado
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

    private void abrirDialogoAgregarDoctor() {
        AgregarDoctor dialogo = new AgregarDoctor(this, "Agregar", true);
        dialogo.setVisible(true);
    }

    private void abrirDialogoEditarDoctor(Doctor doctor) {
        EditarDoctor dialogo = new EditarDoctor(this, "Editar", true);
        dialogo.setDoctor(doctor);
        dialogo.setVisible(true);
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

    private void actualizarDoctores(){
        DoctorCRUD crud = new DoctorCRUD();
        doctores = crud.obtenerRegistro();
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
