package Registros;
import Entidades.Doctor;

import java.io.Serializable;
import java.util.LinkedHashSet;;

//Esta clase unicamente maneja el registro en tiempo de ejecución de los doctores.
public class RegistroDoctor implements Serializable {

    private LinkedHashSet<Doctor> registro = new LinkedHashSet<Doctor>(); //Declaramos una estructura de datos para guardar los objetods de clase Doctor.
    public RegistroDoctor(){
        //Usamos un constructor generico.
    }

    //Este método permite acceder a la función de agregar.
    public void insertarDoctor(Doctor doctor){
        registro.add(doctor);
    }

    public int idDisponible(){
        // Comprobamos si el LinkedHashSet tiene contenido
        if (registro.isEmpty()) {
            return 1; // Si no hay doctores, el primer ID disponible es 1
        }

        int idActual = 1;

        // Iteramos sobre los doctores para encontrar un ID disponible
        for (Doctor doctor : registro) {
            // Verificamos si el ID actual coincide con el ID del doctor
            if (doctor.getId() == idActual) {
                // Si coincide, incrementamos el ID actual
                idActual++;
            } else {
                // Si no coincide, encontramos un ID disponible
                return idActual;
            }
        }

        // Si todos los IDs estaban ocupados, devolvemos el siguiente ID disponible
        return idActual;
    }

    //Getter y setter.
    public LinkedHashSet<Doctor> getRegistro() {
        return registro;
    }
    public void setRegistro(LinkedHashSet<Doctor> registro) {
        this.registro = registro;
    }
}
