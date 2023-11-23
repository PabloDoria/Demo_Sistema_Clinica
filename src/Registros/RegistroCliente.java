package Registros;
import Entidades.Cliente;

import java.util.LinkedHashSet;

//Esta clase unicamente maneja el registro en tiempo de ejecución de los clientes.
public class RegistroCliente {
    private LinkedHashSet<Cliente> registro; //Generamos la estructura de datos para el registro de objetos tipo Cliente.

    public RegistroCliente(){
        //Usamos un constructor génerico.
    }

    //Este método permite acceder a la función de agregar clientes.
    public void insertarDoctor(Cliente cliente){
        registro.add(cliente);
    }

    //Getter y setter.
    public LinkedHashSet<Cliente> getRegistro() {
        return registro;
    }
    public void setRegistro(LinkedHashSet<Cliente> registro) {
        this.registro = registro;
    }
}
