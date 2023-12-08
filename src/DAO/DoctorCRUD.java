package DAO;
import Entidades.Doctor;
import java.io.*;
import java.util.LinkedHashSet;

public class DoctorCRUD extends CRUD<Doctor> implements Serializable{

    public DoctorCRUD(){
        super("src\\ListaDoctores.txt");
    }

}
