package VentanasCita;
import DAO.CitaCRUD;
import DAO.DoctorCRUD;
import DAO.PacienteCRUD;
import Entidades.Cita;
import Entidades.Doctor;
import Entidades.Paciente;
import Registros.RegistroCita;
import Registros.RegistroDoctor;
import Registros.RegistroPaciente;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.LinkedHashSet;

public class EditarCita extends JDialog{
    Cita cita = new Cita();
    private JPanel panel1;
    private JLabel lblIdCita;
    private JLabel lblFecha;
    private JLabel lblDia;
    private JComboBox cmbDia;
    private JComboBox cmbMes;
    private JComboBox cmbAno;
    private JLabel lblMes;
    private JLabel lblAno;
    private JLabel lblHora;
    private JLabel lblHoras;
    private JLabel lblMinuto;
    private JComboBox cmbHora;
    private JComboBox cmbMinuto;
    private JLabel lblMotivo;
    private JTextArea txtaMotivo;
    private JLabel lblEstado;
    private JComboBox cmbEstado;
    private JButton agendarButton;
    private JButton cancelarButton;
    private JTextField txtIdCita;
    private JLabel lblPaciente;
    private JComboBox cmbPaciente;
    private JLabel lblDoctor;
    private JComboBox cmbDoctor;

    public EditarCita(JFrame frame, String titulo, boolean modal) {


        super(frame, titulo, modal);
        setSize(800, 400);
        add(panel1);
        setLocationRelativeTo(frame);

        System.out.println(cita.getMotivo());

        CitaCRUD crudCita = new CitaCRUD();
        RegistroCita registroCita = new RegistroCita();
        registroCita.setRegistro(crudCita.obtenerRegistro());

        DoctorCRUD doctorCRUD = new DoctorCRUD();
        PacienteCRUD pacienteCRUD = new PacienteCRUD();

        RegistroPaciente registroPaciente = new RegistroPaciente();
        RegistroDoctor registroDoctor = new RegistroDoctor();

        registroPaciente.setRegistro(pacienteCRUD.obtenerRegistro());
        registroDoctor.setRegistro(doctorCRUD.obtenerRegistro());

        LinkedHashSet<Doctor> listaDoctores = registroDoctor.getRegistro();
        LinkedHashSet<Paciente> listaPacientes = registroPaciente.getRegistro();

        DefaultComboBoxModel<String> modelDoctores = new DefaultComboBoxModel<>();
        for (Doctor doctor : listaDoctores) {
            String infoDoctor = doctor.getId() + " - " + doctor.getNombre() + " " + doctor.getApellidoP() + " " + doctor.getApellidoM();
            modelDoctores.addElement(infoDoctor);
        }
        cmbDoctor.setModel(modelDoctores);

        DefaultComboBoxModel<String> modelPacientes = new DefaultComboBoxModel<>();
        for (Paciente paciente : listaPacientes) {
            String infoPaciente = paciente.getId() + " - " + paciente.getNombre() + " " + paciente.getApellidoP() + " " + paciente.getApellidoM();
            modelPacientes.addElement(infoPaciente);
        }
        cmbPaciente.setModel(modelPacientes);

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        agendarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtaMotivo.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(EditarCita.this,
                            "Por favor, complete todos los campos.", "Campo Vacío",
                            JOptionPane.WARNING_MESSAGE);
                }

                if(esFechaValida() == true){

                    // Extraer el ID del doctor
                    String doctorInfo = (String) cmbDoctor.getSelectedItem();
                    String doctorId = doctorInfo.split(" - ")[0];
                    cita.setDoctor(doctorCRUD.buscarPorId(Integer.parseInt(doctorId)));

                    // Extraer el ID del paciente
                    String pacienteInfo = (String) cmbPaciente.getSelectedItem();
                    String pacienteId = pacienteInfo.split(" - ")[0];
                    cita.setPaciente(pacienteCRUD.buscarPorId(Integer.parseInt(pacienteId)));

                    cita.setEstado((String)cmbEstado.getSelectedItem());
                    cita.setMotivo(txtaMotivo.getText());
                    cita.setHoraConFormato((String)cmbHora.getSelectedItem() + ":" +(String)cmbMinuto.getSelectedItem());

                    cita.setFecha(LocalDate.of(Integer.parseInt((String)cmbAno.getSelectedItem()), Integer.parseInt((String)cmbMes.getSelectedItem()),
                            Integer.parseInt((String)cmbDia.getSelectedItem())));
                    registroCita.insertarEntidad(cita);
                    crudCita.insertarRegistro(registroCita.getRegistro());

                    dispose();
                } else {
                    // Muestra un JOptionPane de advertencia
                    JOptionPane.showMessageDialog(EditarCita.this,
                            "Por favor, ingrese una fecha válida.", "Fecha no válida",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private boolean esFechaValida() {
        int dia = Integer.parseInt(cmbDia.getSelectedItem().toString());
        int mes = cmbMes.getSelectedIndex() + 1; // Sumamos 1 ya que los meses en Java comienzan desde 0
        int año = Integer.parseInt(cmbAno.getSelectedItem().toString());

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

    public void setCita(Cita cita) {
        this.cita = cita;
        txtaMotivo.setText(cita.getMotivo());
        txtIdCita.setText(String.valueOf(cita.getId()));
        cmbAno.setSelectedItem(String.valueOf(cita.getFecha().getYear()));
        cmbMes.setSelectedItem(String.valueOf(cita.getFecha().getMonth()));
        cmbDia.setSelectedItem(String.valueOf(cita.getFecha().getDayOfMonth()));
        cmbHora.setSelectedItem(String.valueOf(cita.getHora()));
        cmbMinuto.setSelectedItem(String.valueOf(cita.getMinuto()));
        txtIdCita.setEditable(false);
        cmbPaciente.setSelectedItem(String.valueOf(cita.getPaciente().getId()) + " - " + cita.getPaciente().getNombre() + " " +
                cita.getPaciente().getApellidoP() + " " + cita.getPaciente().getApellidoM());
        cmbDoctor.setSelectedItem(String.valueOf(cita.getDoctor().getId()) + " - " + cita.getDoctor().getNombre() + " " +
                cita.getDoctor().getApellidoP() + " " + cita.getDoctor().getApellidoM());
        cmbEstado.setSelectedItem(cita.getEstado());
    }
}
