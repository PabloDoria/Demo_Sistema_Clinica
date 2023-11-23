package Entidades;
import java.util.Date;

public class Cliente extends Usuario{
    // Atributos (variables de instancia)
    private float peso = 0, estatura = 0;
    private char tipo_sangre;

    private Date fecha_nacimiento;

    // Constructores
    public Cliente() {
        // Constructor por defecto
    }

    public Cliente(int id, String nombre, String apellidoP, String apellidoM, float peso, float estatura,
                   char tipo_sangre, Date fecha_nacimiento) {
        super(id, nombre, apellidoP, apellidoM);
        this.peso = peso;
        this.estatura = estatura;
        this.tipo_sangre = tipo_sangre;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public float getPeso() {
        return peso;
    }
    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getEstatura() {
        return estatura;
    }
    public void setEstatura(float estatura) {
        this.estatura = estatura;
    }

    public char getTipo_sangre() {
        return tipo_sangre;
    }
    public void setTipo_sangre(char tipo_sangre) {
        this.tipo_sangre = tipo_sangre;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }
    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
}
