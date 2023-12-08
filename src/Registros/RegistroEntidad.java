package Registros;

import java.io.Serializable;
import java.util.LinkedHashSet;

// Clase padre para el registro de entidades
public class RegistroEntidad<T> implements Serializable {

    private LinkedHashSet<T> registro = new LinkedHashSet<>();

    public RegistroEntidad() {
        // Constructor genérico
    }

    // Método para insertar una entidad en el registro
    public void insertarEntidad(T entidad) {
        registro.add(entidad);
    }

    // Método para obtener un ID disponible
    public int idDisponible() {
        if (registro.isEmpty()) {
            return 1;
        }

        int idActual = 1;

        for (T entidad : registro) {
            // Suponemos que todas las entidades tienen un método getId()
            if (obtenerId(entidad) == idActual) {
                idActual++;
            } else {
                return idActual;
            }
        }

        return idActual;
    }

    // Método para obtener el ID de una entidad
    private int obtenerId(T entidad) {
        // Suponemos que todas las entidades tienen un método getId()
        // Asegúrate de que el tipo T tiene un método getId()
        try {
            return (int) entidad.getClass().getMethod("getId").invoke(entidad);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void eliminarEntidadPorId(int id) {
        T entidadAEliminar = null;

        // Buscar la entidad con el ID proporcionado
        for (T entidad : registro) {
            if (obtenerId(entidad) == id) {
                entidadAEliminar = entidad;
                break;
            }
        }

        // Eliminar la entidad del registro
        if (entidadAEliminar != null) {
            registro.remove(entidadAEliminar);
            System.out.println("Entidad eliminada con ID: " + id);
        } else {
            System.out.println("No se encontró ninguna entidad con ID: " + id);
        }
    }


    // Getter y setter
    public LinkedHashSet<T> getRegistro() {
        return registro;
    }

    public void setRegistro(LinkedHashSet<T> registro) {
        this.registro = registro;
    }
}
