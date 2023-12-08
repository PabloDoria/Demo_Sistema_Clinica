package VentanasDoctor;

import DAO.DoctorCRUD;
import Entidades.Doctor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;

public class EditarDoctor extends JDialog {
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

    public EditarDoctor(JFrame frame, String titulo, boolean modal) {
        super(frame, titulo, modal);
        setSize(1000, 200);
        add(PanelDoctor);
        setLocationRelativeTo(frame);
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

                    crud.insertarRegistro(registro);

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
