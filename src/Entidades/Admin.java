package Entidades;

public class Admin extends Usuario{
    private String usuario = "";
    private String contrasena = "";

    public Admin(){

    }

    public Admin(int id, String nombre, String apellidoP, String apellidoM, String usuario, String contrasena){
        super(id, nombre, apellidoP, apellidoM);
        this.usuario = usuario;
        this.contrasena = contrasena;
    }
}
