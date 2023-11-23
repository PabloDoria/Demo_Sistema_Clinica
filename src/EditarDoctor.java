import DAO.DoctorCRUD;
import Entidades.Doctor;

import javax.print.Doc;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;

public class EditarDoctor extends JFrame {
    private Doctor doctor = new Doctor();
    private JPanel PanelDoctor;
    private JTextField txtIdDoctor;
    private JTextField txtNombre;
    private JTextField txtApellidoM;
    private JTextField txtApellidoP;
    private JLabel lblIdDoctor;
    private JLabel lblNombre;
    private JLabel lblApellidoP;
    private JComboBox cmbEspecialidad;
    private JLabel lblEspecialidad;
    private JLabel lblApellidoM;
    private JLabel lblTurno;
    private JComboBox cmbTurno;
    private JLabel lblCelular;
    private JLabel lblDireccion;
    private JTextField txtDireccion;
    private JTextField txtCelular;
    private JButton confirmarCambiosButton;
    private JButton cancelarButton;

    public EditarDoctor() {

        confirmarCambiosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!(esCampoVacio(txtNombre) && esCampoVacio(txtApellidoM) && esCampoVacio(txtApellidoP) &&
                        esCampoVacio(txtDireccion) && esCampoVacio(txtCelular))) {

                    //Los actualizamos en nuestro archivo.
                    DoctorCRUD crud = new DoctorCRUD();
                    LinkedHashSet<Doctor> registro = crud.obtenerRegistro();

                    for (Doctor doctor : registro) {
                        if (doctor.getId() == Integer.parseInt(txtIdDoctor.getText())) {
                            doctor.setNombre(txtNombre.getText());
                            doctor.setApellidoM(txtApellidoM.getText());
                            doctor.setApellidoP(txtApellidoP.getText());
                            doctor.setCelular(Long.parseLong(txtCelular.getText()));
                            doctor.setEspecialidad((String) cmbEspecialidad.getSelectedItem());
                            doctor.setTurno((String) cmbTurno.getSelectedItem());
                            break;  // Puedes salir del bucle después de modificar el Doctor
                        }
                    }

                    crud.insertarDoctor(registro);

                    dispose();

                } else{
                    JOptionPane.showMessageDialog(PanelDoctor, "Por favor, complete todos los campos.", "Alerta", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void setDoctor(Doctor doctor){
        this.doctor = doctor;
        txtIdDoctor.setText(String.valueOf(doctor.getId()));
        txtIdDoctor.setEditable(false);
        txtNombre.setText(doctor.getNombre());
        txtApellidoP.setText(doctor.getApellidoP());
        txtApellidoM.setText(doctor.getApellidoM());
        cmbEspecialidad.setSelectedItem(doctor.getEspecialidad());
        cmbTurno.setSelectedItem(doctor.getTurno());
        txtDireccion.setText(doctor.getDireccion());
        txtCelular.setText(String.valueOf(doctor.getCelular()));
    }

    public static void main(String[] args) {

        EditarDoctor v = new EditarDoctor();
        v.setContentPane(v.PanelDoctor);
        v.setSize(1000, 200);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.setVisible(true);
    }

    private static boolean esCampoVacio(JTextField textField) {
        return textField.getText().trim().isEmpty();
    }

    public JPanel getPanelDoctor() {
        return PanelDoctor;
    }

    public void setPanelDoctor(JPanel panelDoctor) {
        this.PanelDoctor = panelDoctor;
    }
}
