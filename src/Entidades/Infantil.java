package Entidades;

import java.util.Date;
import java.util.List;

public class Infantil extends Cliente {
    private List<Cliente> tutores;

    // Constructor por defecto
    public Infantil() {
        // Inicializa los valores predeterminados o deja los campos sin inicializar
    }

    // Constructor normal
    // Constructor normal
    public Infantil(int id, String nombre, String apellidoP, String apellidoM, float peso, float estatura,
                    char tipo_sangre, Date fecha_nacimiento, Cliente tutor)
    {
        super(id, nombre, apellidoP, apellidoM, peso, estatura, tipo_sangre, fecha_nacimiento);
        this.tutores.add(tutor);
    }
}
