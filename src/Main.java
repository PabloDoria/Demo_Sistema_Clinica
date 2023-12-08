import DAO.CitaCRUD;
import DAO.PacienteCRUD;
import Entidades.Cita;
import Entidades.Doctor;
import Entidades.Paciente;
import Registros.RegistroCita;
import Registros.RegistroDoctor;
import DAO.DoctorCRUD;
import Registros.RegistroPaciente;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedHashSet;

public class Main {
    public static void main(String[] args) {

        Paciente paciente = new Paciente(
                45,
                "Juan",
                "Perez",
                "Gomez",
                70.5f,
                175.0f,
                "O+",
                LocalDate.of(1990, 5, 15),
                "Calle 123",
                2,  // ID del doctor cabecera
                "juan.perez@example.com",
                123456789
        );

        // Crear instancia de Doctor
        Doctor doctor = new Doctor(
                45,
                "Dr. Carlos",
                "Gonzalez",
                "Lopez",
                "Cardiología",
                "Mañana",
                "Hospital XYZ",
                987654321
        );

        // Crear instancia de Cita y vincular Paciente y Doctor
        Cita cita = new Cita(
                1,
                paciente,
                doctor,
                LocalDate.of(2023, 12, 15),
                LocalTime.of(10, 30),
                "Consulta regular",
                "Programada"
        );

        PacienteCRUD pacienteCRUD = new PacienteCRUD();
        DoctorCRUD doctorCRUD = new DoctorCRUD();
        CitaCRUD citaCRUD = new CitaCRUD();

        RegistroDoctor registroDoctor = new RegistroDoctor();
        RegistroPaciente registroPaciente = new RegistroPaciente();
        RegistroCita registroCita = new RegistroCita();

        LinkedHashSet<Cita> listaCita = new LinkedHashSet<Cita>();

        registroDoctor.setRegistro(doctorCRUD.obtenerRegistro());
        registroDoctor.getRegistro().add(doctor);
        doctorCRUD.insertarRegistro(registroDoctor.getRegistro());

        registroPaciente.setRegistro(pacienteCRUD.obtenerRegistro());
        registroPaciente.getRegistro().add(paciente);
        pacienteCRUD.insertarRegistro(registroPaciente.getRegistro());

        listaCita.add(cita);
        citaCRUD.insertarRegistro(listaCita);



    }
}