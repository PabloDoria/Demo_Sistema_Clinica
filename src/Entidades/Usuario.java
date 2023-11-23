package Entidades;

import java.io.Serializable;

public class Usuario implements Serializable {

    private int id = 0;
    private String nombre = "";
    private String apellidoP = "";
    private String apellidoM = "";

    public Usuario() {

    }

    public Usuario(int id, String nombre, String apellidoP, String apellidoM) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getApellidoP() {
        return apellidoP;
    }
    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }
    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

