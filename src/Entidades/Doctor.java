package Entidades;

import java.io.Serializable;

public class Doctor extends Usuario implements Serializable {
    String especialidad = "", turno = "", direccion = "";
    long celular = 0;

    public Doctor() {

    }

    public Doctor(int id, String nombre, String apellidoP, String apellidoM, String especialidad,
        String turno, String direccion, long celular)
    {
        super(id, nombre, apellidoP, apellidoM);
        this.especialidad = especialidad;
        this.turno = turno;
        this.direccion = direccion;
        this.celular = celular;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public long getCelular() {
        return celular;
    }

    public void setCelular(long celular) {
        this.celular = celular;
    }
}
