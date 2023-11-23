import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarDoctor extends JFrame{
    private JLabel lblIdDoctor;
    private JLabel lblNombre;
    private JTextField txtIdDoctor;
    private JTextField txtNombre;
    private JTextField txtApellidoP;
    private JLabel lblApellidoP;
    private JLabel lblApellidoM;
    private JTextField txtApellidoM;
    private JLabel lblEspecialidad;
    private JComboBox cmbEspecialidad;
    private JLabel lblTurno;
    private JComboBox cmbTurno;
    private JLabel lblDireccion;
    private JLabel lblCelular;
    private JTextField txtDireccion;
    private JTextField txtCelular;
    private JButton btnGenerarDoctor;
    private JButton cancelarButton;
    private JPanel MiPanel;

    public AgregarDoctor() {

        btnGenerarDoctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {

        AgregarDoctor v = new AgregarDoctor();
        v.setContentPane(v.MiPanel);
        v.setSize(800, 200);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.setVisible(true);
    }
}
