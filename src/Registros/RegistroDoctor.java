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

    //Getter y setter.
    public LinkedHashSet<Doctor> getRegistro() {
        return registro;
    }
    public void setRegistro(LinkedHashSet<Doctor> registro) {
        this.registro = registro;
    }
}
