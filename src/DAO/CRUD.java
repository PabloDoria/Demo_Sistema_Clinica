package DAO;

import java.io.*;
import java.util.LinkedHashSet;

public class CRUD<T> implements Serializable {

    private LinkedHashSet<T> registro = new LinkedHashSet<>();
    private String filePath;

    public CRUD(String filePath) {
        this.filePath = filePath;
    }

    public T buscarPorId(int id) {
        LinkedHashSet<T> registros = obtenerRegistro();

        for (T entidad : registros) {
            try {
                int entityId = (int) entidad.getClass().getMethod("getId").invoke(entidad);
                if (entityId == id) {
                    return entidad;
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Manejar la excepción según tus necesidades
            }
        }

        return null;
    }

    public LinkedHashSet<T> obtenerRegistro() {
        try {
            FileInputStream leer = new FileInputStream(filePath);
            ObjectInputStream miStream = new ObjectInputStream(leer);
            Object o = miStream.readObject();
            miStream.close();
            registro = (LinkedHashSet<T>) o;
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return registro;
    }

    public void insertarRegistro(LinkedHashSet<T> registro) {
        try {
            FileOutputStream escribir = new FileOutputStream(filePath);
            ObjectOutputStream miStream = new ObjectOutputStream(escribir);
            miStream.writeObject(registro);
            miStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
