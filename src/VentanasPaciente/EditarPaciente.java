package VentanasPaciente;

import DAO.DoctorCRUD;
import DAO.PacienteCRUD;
import Entidades.Doctor;
import Entidades.Paciente;
import Registros.RegistroPaciente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.LinkedHashSet;

public class EditarPaciente extends JDialog{
    private JPanel panel1;
    private JLabel lblIdPaciente;
    private JTextField txtIdPaciente;
    private JTextField txtNombre;
    private JLabel lblPeso;
    private JTextField txtPeso;
    private JLabel lblNombre;
    private JLabel lblTipoSangre;
    private JComboBox cmbTipoSangre;
    private JLabel lblApellidoP;
    private JLabel lblApellidoM;
    private JTextField txtApellidoP;
    private JTextField txtApellidoM;
    private JComboBox cmbMes;
    private JComboBox cmbAño;
    private JComboBox cmbDia;
    private JLabel lblEstatura;
    private JTextField txtEstatura;
    private JTextField txtDirección;
    private JLabel lblCorreo;
    private JTextField txtCorreo;
    private JTextField txtCelular;
    private JLabel lblCelular;
    private JTextField txtIdDoctor;
    private JLabel lblNombreDoctor;
    private JTextField txtNombreDoctor;
    private JLabel lblApellidoPDoctor;
    private JTextField txtApellidoPDoctor;
    private JButton cancelarButton;
    private JButton guardarCambiosButton;
    private JButton ConfirmarDoctorButton;

    private Doctor doctor = new Doctor();
    private DoctorCRUD dcrud = new DoctorCRUD();
    private Paciente paciente = new Paciente();


    public EditarPaciente(JFrame frame, String titulo, boolean modal) {

        super(frame, titulo, modal);
        setSize(1200, 300);
        add(panel1);
        setLocationRelativeTo(frame);

        guardarCambiosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(camposLlenos() == true && dcrud.buscarPorId(Integer.parseInt(txtIdPaciente.getText()))!= null){

                    if(esFechaValida() == true){

                        PacienteCRUD pcrud = new PacienteCRUD();
                        LinkedHashSet<Paciente> registro = pcrud.obtenerRegistro();

                        for (Paciente paciente1 : registro) {

                            if (paciente1.getId() == Integer.parseInt(txtIdPaciente.getText())) {
                                paciente1.setNombre(txtNombre.getText());
                                paciente1.setApellidoM(txtApellidoM.getText());
                                paciente1.setApellidoP(txtApellidoP.getText());
                                paciente1.setCelular(Integer.parseInt(txtCelular.getText()));
                                paciente1.setPeso(Float.parseFloat(txtPeso.getText()));
                                paciente1.setEstatura(Float.parseFloat(txtEstatura.getText()));
                                paciente1.setTipo_sangre((String)cmbTipoSangre.getSelectedItem());
                                paciente1.setDireccion(txtDirección.getText());
                                paciente1.setFecha_nacimiento(LocalDate.of(Integer.parseInt((String)cmbAño.getSelectedItem()),
                                        Integer.parseInt((String)cmbMes.getSelectedItem()),Integer.parseInt((String)cmbDia.getSelectedItem())));
                                break;

                            }
                        }
                        pcrud.insertarRegistro(registro);
                        dispose();
                    } else {
                        // Muestra un JOptionPane de advertencia
                        JOptionPane.showMessageDialog(EditarPaciente.this,
                                "Por favor, ingrese una fecha válida.", "Fecha no válida",
                                JOptionPane.WARNING_MESSAGE);
                    }

                } else {
                    // Muestra un JOptionPane de advertencia
                    JOptionPane.showMessageDialog(EditarPaciente.this,
                            "Por favor, complete todos los campos.", "Campo Vacío",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        ConfirmarDoctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Doctor doctor = new Doctor();

                doctor = dcrud.buscarPorId(Integer.parseInt(txtIdDoctor.getText()));

                if(doctor == null){
                    // Muestra un JOptionPane de advertencia
                    JOptionPane.showMessageDialog(EditarPaciente.this,
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

    private boolean camposLlenos() {
        // Verifica si todos los campos están llenos
        return !txtNombre.getText().isEmpty() &&
                !txtApellidoP.getText().isEmpty() &&
                !txtApellidoM.getText().isEmpty() &&
                !txtDirección.getText().isEmpty() &&
                !txtPeso.getText().isEmpty() &&
                !txtIdDoctor.getText().isEmpty() &&
                !txtCelular.getText().isEmpty() &&
                cmbTipoSangre.getSelectedItem() != null &&
                !txtEstatura.getText().isEmpty() &&
                cmbDia.getSelectedItem() != null &&
                cmbMes.getSelectedItem() != null &&
                cmbAño.getSelectedItem() != null;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
        txtNombre.setText(paciente.getNombre());
        txtApellidoP.setText(paciente.getApellidoP());
        txtApellidoM.setText(paciente.getApellidoM());
        txtCorreo.setText(paciente.getCorreoElectronico());
        txtCelular.setText((String.valueOf(paciente.getCelular())));
        txtDirección.setText(paciente.getDireccion());
        txtEstatura.setText(String.valueOf(paciente.getEstatura()));
        txtPeso.setText(String.valueOf(paciente.getPeso()));
        cmbAño.setSelectedItem(String.valueOf(paciente.getFecha_nacimiento().getYear()));
        cmbMes.setSelectedItem(String.valueOf(paciente.getFecha_nacimiento().getMonth()));
        cmbDia.setSelectedItem(String.valueOf(paciente.getFecha_nacimiento().getDayOfMonth()));
        cmbTipoSangre.setSelectedItem(paciente.getTipo_sangre());
        txtIdPaciente.setText(String.valueOf(paciente.getId()));
        txtIdPaciente.setEditable(false);
        txtNombreDoctor.setEditable(false);
        txtApellidoPDoctor.setEditable(false);

        txtIdDoctor.setText(String.valueOf(paciente.getDoctorCabecera()));

        doctor = dcrud.buscarPorId(paciente.getDoctorCabecera());
        txtNombreDoctor.setText(doctor.getNombre());
        txtApellidoPDoctor.setText(doctor.getApellidoP());
    }
}

