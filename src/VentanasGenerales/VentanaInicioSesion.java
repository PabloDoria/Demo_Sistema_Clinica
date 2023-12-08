package VentanasGenerales;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaInicioSesion extends JFrame {
    private JPasswordField passwordField1;
    private JTextField txtUsuario;
    private JLabel lblUsuario;
    private JLabel lblContrasena;
    private JButton cancelarButton;
    private JButton ingresarComoInvitadoButton;
    private JButton accederButton;
    private JPanel PanelInicioSesión;
    private JLabel lblTituloClinica;

    public VentanaInicioSesion() {
        accederButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(txtUsuario.getText().equals("Administrador") && String.valueOf(passwordField1.getPassword()).equals("12345")){
                    dispose();
                    JOptionPane.showMessageDialog(PanelInicioSesión, "Bienvenido administrador.", "Saludo",JOptionPane.INFORMATION_MESSAGE);
                    String[] tipoUsuario = {"Administrador"};
                    PaginaPrincipal.main(tipoUsuario);
                }
                else{
                    JOptionPane.showMessageDialog(PanelInicioSesión, "Usuario o contraseña incorrecto.", "Error al acceder", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        ingresarComoInvitadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JOptionPane.showMessageDialog(PanelInicioSesión, "Bienvenido invitado.", "Saludo",JOptionPane.INFORMATION_MESSAGE);
                String[] tipoUsuario = {"Invitado"};
                PaginaPrincipal.main(tipoUsuario);
            }
        });
    }

    public static void main(String[] args){
        VentanaInicioSesion v = new VentanaInicioSesion();
        v.setContentPane(v.PanelInicioSesión);
        v.setSize(300,300);
        v.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        v.setLocationRelativeTo(null);
        v.setVisible(true);
    }
}
