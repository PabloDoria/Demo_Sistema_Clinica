package DAO;

import Entidades.Doctor;
import Entidades.Paciente;

import java.io.*;
import java.util.LinkedHashSet;

public class PacienteCRUD extends CRUD<Paciente> implements Serializable {

    public PacienteCRUD() {
        super("src\\ListaPacientes.txt");
    }

}
