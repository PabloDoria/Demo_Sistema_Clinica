package Entidades;

public class SuperAdmin extends Admin{
    public SuperAdmin(){

    }

    public SuperAdmin(int id, String nombre, String apellidoP, String apellidoM, String usuario, String contrasena){
        super(id, nombre, apellidoP, apellidoM, usuario, contrasena);
    }
}
