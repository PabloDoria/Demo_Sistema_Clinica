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
import VentanasPaciente.AgregarPaciente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.LinkedHashSet;

public class AgregarCita extends JDialog{
    private JPanel panel1;
    private JButton agendarButton;
    private JButton cancelarButton;
    private JLabel lblIdCita;
    private JTextField txtIdCita;
    private JComboBox cmbPaciente;
    private JLabel lblPaciente;
    private JComboBox cmbDoctor;
    private JLabel lblDoctor;
    private JComboBox cmbDia;
    private JComboBox cmbMes;
    private JComboBox cmbAno;
    private JComboBox cmbHora;
    private JComboBox cmbMinuto;
    private JTextArea txtaMotivo;
    private JLabel lblFecha;
    private JLabel lblDia;
    private JLabel lblMes;
    private JLabel lblAno;
    private JLabel lblHora;
    private JLabel lblHoras;
    private JLabel lblMinuto;
    private JLabel lblMotivo;
    private JLabel lblEstado;
    private JComboBox cmbEstado;

    public AgregarCita(JFrame frame, String titulo, boolean modal) {
        super(frame, titulo, modal);
        setSize(800, 400);
        add(panel1);
        setLocationRelativeTo(frame);

        Cita cita = new Cita();
        CitaCRUD crudCita = new CitaCRUD();
        RegistroCita registroCita = new RegistroCita();

        registroCita.setRegistro(crudCita.obtenerRegistro());

        txtIdCita.setText(String.valueOf(registroCita.idDisponible()));
        txtIdCita.setEditable(false);

        DoctorCRUD doctorCRUD = new DoctorCRUD();
        PacienteCRUD pacienteCRUD = new PacienteCRUD();

        RegistroPaciente registroPaciente = new RegistroPaciente();
        RegistroDoctor registroDoctor = new RegistroDoctor();

        registroPaciente.setRegistro(pacienteCRUD.obtenerRegistro());
        registroDoctor.setRegistro(doctorCRUD.obtenerRegistro());

        LinkedHashSet<Doctor> listaDoctores = registroDoctor.getRegistro();
        LinkedHashSet<Paciente> listaPacientes = registroPaciente.getRegistro();


        //AQUI CHATGPT ES ESTO>

        DefaultComboBoxModel<String> modelDoctores = new DefaultComboBoxModel<>();
        for (Doctor doctor : listaDoctores) {
            String infoDoctor = doctor.getId() + " - " + doctor.getNombre() + " " + doctor.getApellidoP() + " " + doctor.getApellidoM();
            modelDoctores.addElement(infoDoctor);
        }
        cmbDoctor.setModel(modelDoctores);

        System.out.println("tamaño " + listaDoctores.size());


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
                    JOptionPane.showMessageDialog(AgregarCita.this,
                            "Por favor, complete todos los campos.", "Campo Vacío",
                            JOptionPane.WARNING_MESSAGE);
                }

                if(esFechaValida() == true){

                    cita.setId(registroCita.idDisponible());
                    String doctorId = (String)cmbDoctor.getSelectedItem();
                    doctorId = ((String) cmbDoctor.getSelectedItem()).replaceAll("\\D", "");
                    cita.setDoctor(doctorCRUD.buscarPorId(Integer.parseInt(doctorId)));

                    String pacienteId = (String)cmbPaciente.getSelectedItem();
                    pacienteId = ((String) cmbPaciente.getSelectedItem()).replaceAll("\\D", "");
                    cita.setPaciente(pacienteCRUD.buscarPorId(Integer.parseInt(pacienteId)));

                    cita.setEstado((String)cmbEstado.getSelectedItem());
                    cita.setMotivo(txtaMotivo.getText());
                    System.out.println((String)cmbHora.getSelectedItem() + ":" +(String)cmbMinuto.getSelectedItem());
                    cita.setHoraConFormato((String)cmbHora.getSelectedItem() + ":" +(String)cmbMinuto.getSelectedItem());

                    cita.setFecha(LocalDate.of(Integer.parseInt((String)cmbAno.getSelectedItem()), Integer.parseInt((String)cmbMes.getSelectedItem()),
                            Integer.parseInt((String)cmbDia.getSelectedItem())));
                    registroCita.insertarEntidad(cita);
                    crudCita.insertarRegistro(registroCita.getRegistro());

                    dispose();
                } else {
                    // Muestra un JOptionPane de advertencia
                    JOptionPane.showMessageDialog(AgregarCita.this,
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
}
