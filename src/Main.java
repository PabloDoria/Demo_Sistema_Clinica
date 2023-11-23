import Entidades.Doctor;
import Registros.RegistroDoctor;
import DAO.DoctorCRUD;

public class Main {
    public static void main(String[] args) {

        RegistroDoctor registro1 = new RegistroDoctor();
        Doctor doctor1 = new Doctor(1, "Juan", "Pérez", "Gómez", "General", "Matutino", "Calle A #123", 1234567890L);
        Doctor doctor2 = new Doctor(2, "María", "García", "López", "General", "Matutino", "Avenida B #456", 9876543210L);
        Doctor doctor3 = new Doctor(3, "Luis", "Rodríguez", "Martínez", "General", "Matutino", "Calle C #789", 5678901234L);
        Doctor doctor4 = new Doctor(4, "Ana", "Martínez", "Hernández", "General", "Matutino", "Avenida D #1011", 4321098765L);
        Doctor doctor5 = new Doctor(5, "Pedro", "Gómez", "Sánchez", "General", "Matutino", "Calle E #1213", 2109876543L);
        Doctor doctor6 = new Doctor(6, "Sofía", "Hernández", "García", "General", "Matutino", "Avenida F #1415", 1357924680L);
        Doctor doctor7 = new Doctor(7, "Carlos", "López", "Ramírez", "General", "Matutino", "Calle G #1617", 2468013579L);
        Doctor doctor8 = new Doctor(8, "Laura", "Ramírez", "Gómez", "General", "Matutino", "Avenida H #1819", 9870123456L);
        Doctor doctor9 = new Doctor(9, "Javier", "Sánchez", "Rodríguez", "General", "Matutino", "Calle I #2021", 6543210987L);
        Doctor doctor10 = new Doctor(10, "Isabel", "Gómez", "Pérez", "General", "Matutino", "Avenida J #2223", 3210987654L);
        Doctor doctor11 = new Doctor(11, "Diego", "Martínez", "Sánchez", "General", "Matutino", "Calle K #2425", 7890123456L);
        Doctor doctor12 = new Doctor(12, "Elena", "Ramírez", "Hernández", "General", "Matutino", "Avenida L #2627", 5678901234L);
        Doctor doctor13 = new Doctor(13, "Miguel", "García", "López", "General", "Matutino", "Calle M #2829", 4321098765L);
        Doctor doctor14 = new Doctor(14, "Carmen", "Hernández", "Ramírez", "General", "Matutino", "Avenida N #3031", 2109876543L);
        Doctor doctor15 = new Doctor(15, "Ricardo", "Sánchez", "Gómez", "General", "Matutino", "Calle O #3233", 1357924680L);
        Doctor doctor16 = new Doctor(16, "Paula", "López", "Martínez", "General", "Matutino", "Avenida P #3435", 2468013579L);
        Doctor doctor17 = new Doctor(17, "Hugo", "Pérez", "Sánchez", "General", "Matutino", "Calle Q #3637", 9870123456L);
        Doctor doctor18 = new Doctor(18, "Adriana", "Martínez", "García", "General", "Matutino", "Avenida R #3839", 6543210987L);
        Doctor doctor19 = new Doctor(19, "Roberto", "Gómez", "Ramírez", "General", "Matutino", "Calle S #4041", 3210987654L);
        Doctor doctor20 = new Doctor(20, "Silvia", "Hernández", "López", "General", "Matutino", "Avenida T #4243", 7890123456L);
        Doctor doctor21 = new Doctor(21, "Alejandro", "Ramírez", "Sánchez", "General", "Matutino", "Calle U #4445", 5678901234L);
        Doctor doctor22 = new Doctor(22, "Fernanda", "García", "Martínez", "General", "Matutino", "Avenida V #4647", 4321098765L);
        Doctor doctor23 = new Doctor(23, "Arturo", "Sánchez", "Pérez", "General", "Matutino", "Calle W #4849", 2109876543L);
        Doctor doctor24 = new Doctor(24, "Alicia", "Pérez", "Ramírez", "General", "Matutino", "Avenida X #5051", 1357924680L);
        Doctor doctor25 = new Doctor(25, "Mario", "Martínez", "Hernández", "General", "Matutino", "Calle Y #5253", 2468013579L);
        Doctor doctor26 = new Doctor(26, "Ximena", "Ramírez", "Gómez", "General", "Matutino", "Avenida Z #5455", 9870123456L);
        Doctor doctor27 = new Doctor(27, "Gabriel", "Gómez", "López", "General", "Matutino", "Calle AA #5657", 6543210987L);
        Doctor doctor28 = new Doctor(28, "Valentina", "Hernández", "Sánchez", "General", "Matutino", "Avenida BB #5859", 3210987654L);
        Doctor doctor29 = new Doctor(29, "Daniel", "Sánchez", "Martínez", "General", "Matutino", "Calle CC #6061", 7890123456L);
        Doctor doctor30 = new Doctor(30, "Sara", "López", "Gómez", "General", "Matutino", "Avenida DD #6263", 5678901234L);
        ;

        System.out.println(doctor1.getNombre());

        DoctorCRUD crud1 = new DoctorCRUD();

        registro1.insertarDoctor(doctor1);
        registro1.insertarDoctor(doctor2);
        registro1.insertarDoctor(doctor3);
        registro1.insertarDoctor(doctor4);
        registro1.insertarDoctor(doctor5);
        registro1.insertarDoctor(doctor6);
        registro1.insertarDoctor(doctor7);
        registro1.insertarDoctor(doctor8);
        registro1.insertarDoctor(doctor9);
        registro1.insertarDoctor(doctor10);
        registro1.insertarDoctor(doctor11);
        registro1.insertarDoctor(doctor12);
        registro1.insertarDoctor(doctor13);
        registro1.insertarDoctor(doctor14);
        registro1.insertarDoctor(doctor15);
        registro1.insertarDoctor(doctor16);
        registro1.insertarDoctor(doctor17);
        registro1.insertarDoctor(doctor18);
        registro1.insertarDoctor(doctor19);
        registro1.insertarDoctor(doctor20);
        registro1.insertarDoctor(doctor21);
        registro1.insertarDoctor(doctor22);
        registro1.insertarDoctor(doctor23);
        registro1.insertarDoctor(doctor24);
        registro1.insertarDoctor(doctor25);
        registro1.insertarDoctor(doctor26);
        registro1.insertarDoctor(doctor27);
        registro1.insertarDoctor(doctor28);
        registro1.insertarDoctor(doctor29);
        registro1.insertarDoctor(doctor30);

        System.out.println("Hola");
        crud1.insertarDoctor(registro1.getRegistro());

        System.out.println(crud1.buscarDoctorId(2).getNombre() + "Hola");
    }
}