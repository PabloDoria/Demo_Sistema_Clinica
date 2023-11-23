package Entidades;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cita {
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
    public LocalTime getHora() {
        return hora;
    }
    public String getMotivo() {
        return motivo;
    }
    public String getEstado() {
        return estado;
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
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}

