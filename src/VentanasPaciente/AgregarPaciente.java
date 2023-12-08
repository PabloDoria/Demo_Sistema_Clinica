package VentanasPaciente;

import Entidades.Doctor;
import DAO.DoctorCRUD;
import DAO.PacienteCRUD;
import Entidades.Paciente;
import Registros.RegistroDoctor;
import Registros.RegistroPaciente;
import VentanasDoctor.AgregarDoctor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class AgregarPaciente extends JDialog {

    private JPanel panel1;
    private JTextField txtIdPaciente;
    private JTextField txtNombre;
    private JTextField txtPeso;
    private JTextField txtEstatura;
    private JLabel lblIdPaciente;
    private JLabel lblTipoSangre;
    private JComboBox cmbTipoSangre;
    private JLabel lblNombre;
    private JLabel lblApellidoP;
    private JTextField txtApellidoP;
    private JTextField txtApellidoM;
    private JLabel lblApellidoM;
    private JLabel lblPeso;
    private JLabel lblEstatura;
    private JTextField txtDirección;
    private JLabel lblCorreo;
    private JTextField txtCorreo;
    private JLabel lblCelular;
    private JTextField txtCelular;
    private JComboBox cmbDia;
    private JComboBox cmbMes;
    private JComboBox cmbAño;
    private JButton agregarPacienteButton;
    private JButton cancelarButton;
    private JTextField txtIdDoctor;
    private JLabel lblNombreDoctor;
    private JTextField txtNombreDoctor;
    private JLabel lblApellidoPDoctor;
    private JTextField txtApellidoPDoctor;
    private JButton ConfirmarDoctorButton;


    public AgregarPaciente(JFrame frame, String titulo, boolean modal){
        super(frame, titulo, modal);
        setSize(1100, 260);
        add(panel1);
        setLocationRelativeTo(frame);

        Paciente paciente = new Paciente();
        PacienteCRUD pcrud = new PacienteCRUD();
        DoctorCRUD dcrud = new DoctorCRUD();
        RegistroPaciente registro = new RegistroPaciente();

        registro.setRegistro(pcrud.obtenerRegistro());
        txtNombreDoctor.setEditable(false);
        txtApellidoPDoctor.setEditable(false);

        txtIdPaciente.setText(String.valueOf(registro.idDisponible()));
        paciente.setId(registro.idDisponible());
        txtIdPaciente.setEditable(false);

        agregarPacienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(camposLlenos() == true && dcrud.buscarPorId(Integer.parseInt(txtIdDoctor.getText()))!= null){

                    if(esFechaValida() == true){

                        paciente.setNombre(txtNombre.getText());
                        paciente.setApellidoP(txtApellidoP.getText());
                        paciente.setApellidoM(txtApellidoM.getText());
                        paciente.setCelular(Integer.parseInt(txtCelular.getText()));
                        paciente.setDireccion(txtDirección.getText());
                        paciente.setCorreoElectronico(txtCorreo.getText());
                        paciente.setEstatura(Float.parseFloat(txtEstatura.getText()));
                        paciente.setPeso(Float.parseFloat(txtPeso.getText()));
                        paciente.setTipo_sangre((String)cmbTipoSangre.getSelectedItem());
                        paciente.setFecha_nacimiento(LocalDate.of(Integer.parseInt((String)cmbAño.getSelectedItem()), Integer.parseInt((String)cmbMes.getSelectedItem()),
                                Integer.parseInt((String)cmbDia.getSelectedItem())));
                        registro.insertarEntidad(paciente);
                        pcrud.insertarRegistro(registro.getRegistro());

                        dispose();
                    } else {
                        // Muestra un JOptionPane de advertencia
                        JOptionPane.showMessageDialog(AgregarPaciente.this,
                                "Por favor, ingrese una fecha válida.", "Fecha no válida",
                                JOptionPane.WARNING_MESSAGE);
                    }

                } else {
                    // Muestra un JOptionPane de advertencia
                    JOptionPane.showMessageDialog(AgregarPaciente.this,
                            "Por favor, complete todos los campos.", "Campo Vacío",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        ConfirmarDoctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Doctor doctor = new Doctor();

                doctor = dcrud.buscarPorId(Integer.parseInt(txtIdDoctor.getText()));

                if(doctor == null){
                    // Muestra un JOptionPane de advertencia
                    JOptionPane.showMessageDialog(AgregarPaciente.this,
                            "Por favor, ingrese un ID de doctor válido.", "Doctor no válido",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    paciente.setDoctorCabecera(Integer.parseInt(txtIdDoctor.getText()));
                    txtNombreDoctor.setText(doctor.getNombre());
                    txtApellidoPDoctor.setText(doctor.getApellidoP());
                }
            }
        });
    }

    private boolean camposLlenos() {
        // Verifica si todos los campos están llenos
        return !txtNombre.getText().isEmpty() &&
                !txtApellidoP.getText().isEmpty() &&
                !txtApellidoM.getText().isEmpty() &&
                !txtDirección.getText().isEmpty() &&
                !txtPeso.getText().isEmpty() &&
                !txtCelular.getText().isEmpty() &&
                cmbTipoSangre.getSelectedItem() != null &&
                !txtEstatura.getText().isEmpty() &&
                cmbDia.getSelectedItem() != null &&
                cmbMes.getSelectedItem() != null &&
                cmbAño.getSelectedItem() != null;
    }

    private boolean esFechaValida() {
        int dia = Integer.parseInt(cmbDia.getSelectedItem().toString());
        int mes = cmbMes.getSelectedIndex() + 1; // Sumamos 1 ya que los meses en Java comienzan desde 0
        int año = Integer.parseInt(cmbAño.getSelectedItem().toString());

        // Verificar si el año es bisiesto
        boolean esBisiesto = (año % 4 == 0 && año % 100 != 0) || (año % 400 == 0);

        // Array con la cantidad de días válidos para cada mes
        int[] diasPorMes = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // Ajustar la cantidad de días para febrero si el año es bisiesto
        if (esBisiesto) {
            diasPorMes[2] = 29;
        }

        // Verificar si el día seleccionado es válido para el mes y año dados
        return dia >= 1 && dia <= diasPorMes[mes];
    }


}
