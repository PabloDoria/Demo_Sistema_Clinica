package VentanasDoctor;

import DAO.DoctorCRUD;
import Entidades.Doctor;
import Registros.RegistroDoctor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarDoctor extends JDialog{
    private JLabel lblIdDoctor;
    private JLabel lblNombre;
    private JTextField txtIdDoctor;
    private JTextField txtNombre;
    private JTextField txtApellidoP;
    private JLabel lblApellidoP;
    private JLabel lblApellidoM;
    private JTextField txtApellidoM;
    private JLabel lblEspecialidad;
    private JComboBox cmbEspecialidad;
    private JLabel lblTurno;
    private JComboBox cmbTurno;
    private JLabel lblDireccion;
    private JLabel lblCelular;
    private JTextField txtDireccion;
    private JTextField txtCelular;
    private JButton btnGenerarDoctor;
    private JButton cancelarButton;
    private JPanel MiPanel;

    public AgregarDoctor(JFrame frame, String titulo, boolean modal) {
        super(frame, titulo, modal);
        setSize(800, 200);
        add(MiPanel);
        setLocationRelativeTo(frame);

        Doctor doctor = new Doctor();
        DoctorCRUD crud = new DoctorCRUD();
        RegistroDoctor registro = new RegistroDoctor();

        registro.setRegistro(crud.obtenerRegistro());

        doctor.setId(registro.idDisponible());
        txtIdDoctor.setText(String.valueOf(registro.idDisponible()));
        System.out.println(registro.idDisponible());
        txtIdDoctor.setEditable(false);

        btnGenerarDoctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                doctor.setNombre(txtNombre.getText());
                doctor.setApellidoP(txtApellidoP.getText());
                doctor.setApellidoM(txtApellidoM.getText());
                doctor.setDireccion(txtDireccion.getText());
                doctor.setCelular(Long.parseLong(txtCelular.getText()));
                doctor.setTurno((String)cmbTurno.getSelectedItem());
                doctor.setEspecialidad((String)cmbEspecialidad.getSelectedItem());


                registro.insertarDoctor(doctor);
                crud.insertarDoctor(registro.getRegistro());

                dispose();
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { dispose(); }
        });
    }

    public JPanel getMiPanel() {
        return MiPanel;
    }

    public static void main(String[] args) {

    }
}
