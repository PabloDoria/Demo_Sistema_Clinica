package Entidades;

import java.time.LocalDate;
import java.util.Date;

public class Paciente extends Cliente{
    private String direccion = "";
    private int doctor_cabecera = 0;
    private String correo_electronico = "";
    private int celular = 0;

    public Paciente() {
        // Inicializa los valores predeterminados o deja los campos sin inicializar
    }

    // Constructor normal
    public Paciente(int id, String nombre, String apellidoP, String apellidoM, float peso, float estatura,
                    String tipo_sangre, LocalDate fecha_nacimiento, String direccion, int doctorCabecera,
                    String correo_electronico, int celular) {
        super(id, nombre, apellidoP, apellidoM, peso, estatura, tipo_sangre, fecha_nacimiento);
        this.direccion = direccion;
        this.doctor_cabecera = doctorCabecera;
        this.correo_electronico = correo_electronico;
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }
    public int getDoctorCabecera() {
        return doctor_cabecera;
    }
    public String getCorreoElectronico() {
        return correo_electronico;
    }
    public int getCelular() {
        return celular;
    }

    // Métodos Setter
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public void setDoctorCabecera(int doctorCabecera) {
        this.doctor_cabecera = doctorCabecera;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correo_electronico = correoElectronico;
    }
    public void setCelular(int celular) {
        this.celular = celular;
    }
}
