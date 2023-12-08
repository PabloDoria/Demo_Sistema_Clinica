package Entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Cita implements Serializable {
    private int id = 0;
    private Paciente paciente;
    private Doctor doctor;
    private LocalDate fecha;
    private LocalTime hora;
    String motivo = "", estado = "";

    public Cita() {
        // Constructor por defecto
    }

    public Cita(int id, Paciente paciente, Doctor doctor, LocalDate fecha, LocalTime hora, String motivo, String estado) {
        this.id = id;
        this.paciente = paciente;
        this.doctor = doctor;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.estado = estado;
    }

    // Métodos Getter
    public int getId() {
        return id;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public Doctor getDoctor() {
        return doctor;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public String getFormattedHora() {
        // Define un formato para la hora sin milisegundos
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        // Formatea la hora utilizando el formatter
        return hora.format(formatter);
    }
    public String getMotivo() {
        return motivo;
    }
    public String getEstado() {
        return estado;
    }

    public int getHora() {
        return hora.getHour();
    }


    // Métodos Setter
    public void setId(int id) {
        this.id = id;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public void setHoraConFormato(String horaConFormato) {
        // Define un formato para la hora sin milisegundos
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

        try {
            // Convierte la cadena de texto al objeto LocalTime utilizando el formatter
            this.hora = LocalTime.parse(horaConFormato, formatter);
        } catch (DateTimeParseException e) {
            // Imprime la excepción para depuración
            e.printStackTrace();
        }
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public int getMinuto() {
        return hora.getMinute();
    }

}

