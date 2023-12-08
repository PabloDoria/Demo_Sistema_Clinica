package Entidades;
import java.time.LocalDate;

public class Cliente extends Usuario {
    // Atributos (variables de instancia)
    private float peso = 0, estatura = 0;
    private String tipo_sangre;

    private LocalDate fecha_nacimiento;

    // Constructores
    public Cliente() {
        // Constructor por defecto
    }

    public Cliente(int id, String nombre, String apellidoP, String apellidoM, float peso, float estatura,
                   String tipo_sangre, LocalDate fecha_nacimiento) {
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

    public String getTipo_sangre() {
        return tipo_sangre;
    }
    public void setTipo_sangre(String tipo_sangre) {
        this.tipo_sangre = tipo_sangre;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }
    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
}
