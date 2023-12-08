package DAO;

import Entidades.Cita;

import java.io.Serializable;

public class CitaCRUD extends CRUD<Cita> implements Serializable {
    public CitaCRUD(){
        super("src\\ListaCitas.txt");
    }
}
