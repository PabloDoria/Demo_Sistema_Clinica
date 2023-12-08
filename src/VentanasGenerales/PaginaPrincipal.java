package VentanasGenerales;

import DAO.CitaCRUD;
import DAO.DoctorCRUD;
import DAO.PacienteCRUD;
import Entidades.Cita;
import Entidades.Doctor;
import Entidades.Paciente;
import Registros.RegistroCita;
import Registros.RegistroDoctor;
import Registros.RegistroPaciente;
import VentanasCita.AgregarCita;
import VentanasCita.EditarCita;
import VentanasDoctor.AgregarDoctor;
import VentanasDoctor.EditarDoctor;
import VentanasGenerales.Paneles.PanelCitas;
import VentanasGenerales.Paneles.PanelDoctores;
import VentanasGenerales.Paneles.PanelPacientes;
import VentanasPaciente.AgregarPaciente;
import VentanasPaciente.EditarPaciente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;
import java.util.Vector;

public class PaginaPrincipal extends JFrame {

    private String tipoUsuarioActual;
    private JButton buscarButton;
    private JTextField txtBuscar;
    private JButton agregarButton;
    private JButton modificarButton;
    private JButton quitarButton;
    private JTabbedPane tbdPaneGeneral;
    private JPanel MiPanel;
    private JPanel PanelTabla = new JPanel();
    private int selectedIndex = tbdPaneGeneral.getSelectedIndex();

    DoctorCRUD crud = new DoctorCRUD();
    CitaCRUD citaCRUD = new CitaCRUD();
    PacienteCRUD crudPaciente = new PacienteCRUD();
    CitaCRUD crudCita = new CitaCRUD();
    LinkedHashSet<Doctor> doctores = crud.obtenerRegistro();
    LinkedHashSet<Paciente> pacientes = crudPaciente.obtenerRegistro();
    LinkedHashSet<Cita> citas = citaCRUD.obtenerRegistro();

    RegistroDoctor registro = new RegistroDoctor();

    RegistroPaciente registroPaciente = new RegistroPaciente();

    RegistroCita registroCita = new RegistroCita();

    public PaginaPrincipal(String[] tipoUsuario) {

        if(tipoUsuario[0].equals("Invitado")){
            modificarButton.setEnabled(false);
            agregarButton.setEnabled(false);
            quitarButton.setEnabled(false);
        }

        PanelDoctores panelDoctores = new PanelDoctores();
        tbdPaneGeneral.add("Doctores", panelDoctores);
        // Obtener el índice de la pestaña actualmente seleccionada


        PanelPacientes panelPacientes = new PanelPacientes();
        tbdPaneGeneral.add("Pacientes",panelPacientes);

        PanelCitas panelCitas = new PanelCitas();
        tbdPaneGeneral.add("Citas",panelCitas);


        // Agregar ActionListener al botón "Modificar"
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tbdPaneGeneral.getSelectedIndex();

                if(index==0){
                    if (panelDoctores.getDoctorSeleccionado() != null) {
                        // Llamar a la función de modificación en otra ventana
                        System.out.println("Modificando doctores seleccionados...");

                        abrirDialogoEditarDoctor(panelDoctores.getDoctorSeleccionado());
                        panelDoctores.setDoctorSeleccionado(null);
                        panelDoctores.llenarTabla();
                    }

                    else {
                        JOptionPane.showMessageDialog(PaginaPrincipal.this, "Seleccione al menos un registro para modificar.",
                                "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                }
                if(index==1){
                    if(panelPacientes.getPacienteSeleccionado() != null){

                        System.out.println(panelPacientes.getPacienteSeleccionado().getFecha_nacimiento());
                        abrirDialogoEditarPaciente(panelPacientes.getPacienteSeleccionado());
                        panelPacientes.setPacienteSeleccionado(null);
                        panelPacientes.llenarTabla();
                    } else {
                        JOptionPane.showMessageDialog(PaginaPrincipal.this, "Seleccione al menos un registro para modificar.",
                                "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                }
                if(index==2){
                    if(panelCitas.getCitaSeleccionada() != null){

                        System.out.println(panelCitas.getCitaSeleccionada().getId());
                        abrirDialogoEditarCita(panelCitas.getCitaSeleccionada());
                        System.out.println(panelCitas.getCitaSeleccionada().getMotivo());
                        panelCitas.setCitaSeleccionada(null);
                        panelCitas.llenarTabla();
                    } else {
                        JOptionPane.showMessageDialog(PaginaPrincipal.this, "Seleccione al menos un registro para modificar.",
                                "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tbdPaneGeneral.getSelectedIndex();
                if(index == 0){
                    abrirDialogoAgregarDoctor();
                    panelDoctores.llenarTabla();
                }
                if(index == 1){
                    abrirDialogoAgregarPaciente();
                    panelPacientes.llenarTabla();
                }
                if(index== 2){
                    abrirDialogoAgregarCita();
                    panelCitas.llenarTabla();
                }

            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tbdPaneGeneral.getSelectedIndex();

                if(index == 0){
                    Doctor doctor = crud.buscarPorId(Integer.parseInt(txtBuscar.getText()));

                    if (doctor == null) {
                        JOptionPane.showMessageDialog(PaginaPrincipal.this, "Ingrese un Id válido.",
                                "Advertencia", JOptionPane.WARNING_MESSAGE);
                    } else {
                        abrirDialogoEditarDoctor(doctor);
                        panelDoctores.llenarTabla();
                    }
                }
                if(index==1){
                    Paciente paciente = crudPaciente.buscarPorId(Integer.parseInt(txtBuscar.getText()));

                    if (paciente == null) {
                        JOptionPane.showMessageDialog(PaginaPrincipal.this, "Ingrese un Id válido.",
                                "Advertencia", JOptionPane.WARNING_MESSAGE);
                    } else {
                        abrirDialogoEditarPaciente(paciente);
                        panelPacientes.llenarTabla();
                    }
                }
                if(index==2){

                    Cita cita = crudCita.buscarPorId(Integer.parseInt(txtBuscar.getText()));

                    if (cita == null) {
                        JOptionPane.showMessageDialog(PaginaPrincipal.this, "Ingrese un Id válido.",
                                "Advertencia", JOptionPane.WARNING_MESSAGE);
                    } else {
                        abrirDialogoEditarCita(cita);
                        panelCitas.llenarTabla();
                    }
                }
            }
        });
        quitarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tbdPaneGeneral.getSelectedIndex();

                if (index == 0) {
                    Doctor doctor = panelDoctores.getDoctorSeleccionado();
                    if (doctor != null) {
                        int confirmacion = JOptionPane.showConfirmDialog(
                                PaginaPrincipal.this,
                                "¿Seguro que desea eliminar al doctor " + doctor.getNombre() + "?",
                                "Confirmar Eliminación",
                                JOptionPane.YES_NO_OPTION);

                        if (confirmacion == JOptionPane.YES_OPTION) {
                            // Usuario ha confirmado la eliminación
                            registro.setRegistro(doctores);
                            registro.eliminarEntidadPorId(doctor.getId());
                            crud.insertarRegistro(registro.getRegistro());
                            panelDoctores.llenarTabla();
                        }
                    } else {
                        JOptionPane.showMessageDialog(PaginaPrincipal.this,
                                "Seleccione un registro para eliminar.",
                                "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                }

                if (index == 1) {
                    Paciente paciente = panelPacientes.getPacienteSeleccionado();

                    if (paciente != null) {
                        int confirmacion = JOptionPane.showConfirmDialog(
                                PaginaPrincipal.this,
                                "¿Seguro que desea eliminar al paciente " + paciente.getNombre() + "?",
                                "Confirmar Eliminación",
                                JOptionPane.YES_NO_OPTION);

                        if (confirmacion == JOptionPane.YES_OPTION) {
                            // Usuario ha confirmado la eliminación
                            registroPaciente.setRegistro(pacientes);
                            registroPaciente.eliminarEntidadPorId(paciente.getId());
                            crudPaciente.insertarRegistro(registroPaciente.getRegistro());
                            panelPacientes.llenarTabla();
                        }
                    } else {
                        JOptionPane.showMessageDialog(PaginaPrincipal.this,
                                "Seleccione un registro para eliminar.",
                                "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                }

                if (index == 2) {
                    Cita cita = panelCitas.getCitaSeleccionada();

                    if (cita != null) {
                        int confirmacion = JOptionPane.showConfirmDialog(
                                PaginaPrincipal.this,
                                "¿Seguro que desea eliminar la cita " + cita.getId() + "?",
                                "Confirmar Eliminación",
                                JOptionPane.YES_NO_OPTION);

                        if (confirmacion == JOptionPane.YES_OPTION) {
                            // Usuario ha confirmado la eliminación
                            registroCita.setRegistro(citas);
                            registroCita.eliminarEntidadPorId(cita.getId());
                            citaCRUD.insertarRegistro(registroCita.getRegistro());
                            panelCitas.llenarTabla();
                        }
                    } else {
                        JOptionPane.showMessageDialog(PaginaPrincipal.this,
                                "Seleccione un registro para eliminar.",
                                "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

    }

    private void abrirDialogoAgregarDoctor() {
        AgregarDoctor dialogo = new AgregarDoctor(this, "Agregar", true);
        dialogo.setVisible(true);
    }

    private void abrirDialogoEditarDoctor(Doctor doctor) {
        EditarDoctor dialogo = new EditarDoctor(this, "Editar", true);
        dialogo.setDoctor(doctor);
        dialogo.setVisible(true);
    }

    private void abrirDialogoAgregarPaciente(){
        AgregarPaciente dialogo = new AgregarPaciente(this,"Agregar", true);
        dialogo.setVisible(true);
    }

    private void abrirDialogoEditarPaciente(Paciente paciente){
        EditarPaciente dialogo = new EditarPaciente(this, "Editar",true);
        dialogo.setPaciente(paciente);
        dialogo.setVisible(true);
    }

    private void abrirDialogoAgregarCita(){
        AgregarCita dialogo = new AgregarCita(this,"Agregar", true);
        dialogo.setVisible(true);
    }

    private void abrirDialogoEditarCita(Cita cita){
        EditarCita dialogo = new EditarCita(this,"Agregar", true);
        dialogo.setCita(cita);
        dialogo.setVisible(true);


    }

    public static void main(String[] args) {
        PaginaPrincipal v = new PaginaPrincipal(args);
        v.setContentPane(v.MiPanel);
        v.setSize(1920, 1080);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.setVisible(true);
    }
}
