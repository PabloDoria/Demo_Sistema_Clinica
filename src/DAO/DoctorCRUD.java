package DAO;
import Entidades.Doctor;
import java.io.*;
import java.util.LinkedHashSet;

public class DoctorCRUD implements Serializable{

    private LinkedHashSet<Doctor> registro = new LinkedHashSet<>();

    public Doctor buscarDoctorId(int id){

        try{
            FileInputStream leer =
                    new FileInputStream("C:\\Users\\pablo\\Documents\\BaseDeDatos.txt");
            ObjectInputStream miStream = new ObjectInputStream(leer);
            Object o = miStream.readObject();
            miStream.close();
            registro = (LinkedHashSet<Doctor>) o;
        }catch(FileNotFoundException e){
            System.out.println("Archivo no encontrado");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (Doctor doctor : registro) {
            if (doctor.getId() == id) {
                return doctor;
            }
        }

        return null;
    }

    public LinkedHashSet<Doctor> obtenerRegistro(){

        try{
            FileInputStream leer =
                    new FileInputStream("C:\\Users\\pablo\\Documents\\BaseDeDatos.txt");
            ObjectInputStream miStream = new ObjectInputStream(leer);
            Object o = miStream.readObject();
            miStream.close();
            registro = (LinkedHashSet<Doctor>) o;
        }catch(FileNotFoundException e){
            System.out.println("Archivo no encontrado");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return registro;
    }

    public void insertarDoctor(LinkedHashSet<Doctor> registro)
    {
        try{
            FileOutputStream escribir =
                    new FileOutputStream("C:\\Users\\pablo\\Documents\\BaseDeDatos.txt");
            ObjectOutputStream miStream =
                    new ObjectOutputStream(escribir);
            miStream.writeObject(registro);
            miStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
