import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class registrar extends JFrame{

    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JButton registarButton;
    private JButton volverButton;
    private JButton buscarButton;

    public registrar(){
        setTitle("Registrar");
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu m = new menu();
                m.setVisible(true);
                dispose();
            }
        });

        registarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    IngresarDatos();
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscar b = new buscar();
                b.setVisible(true);
                dispose();
            }
        });
    }

    public void IngresarDatos()throws SQLException {
        String cedula = textField1.getText();
        String historial = textField2.getText();
        String nombre = textField3.getText();
        String apellido = textField4.getText();
        String telefono = textField5.getText();
        String edad = textField6.getText();
        String descripcion = textField7.getText();

        Connection conectamos = connection();
        String sql = "INSERT INTO PACIENTE(cedula, n_historial_clinico, nombre, apellido, telefono, edad, descripcion_enfermedad)" +
                "VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = conectamos.prepareStatement(sql);
        pstmt.setString(1, cedula);
        pstmt.setInt(2, Integer.parseInt(historial));
        pstmt.setString(3, nombre);
        pstmt.setString(4, apellido);
        pstmt.setString(5, telefono);
        pstmt.setInt(6, Integer.parseInt(edad));
        pstmt.setString(7, descripcion);

        int rowAffected= pstmt.executeUpdate();
        if(rowAffected >0){
            JOptionPane.showMessageDialog(null, "Registro insertado correctamente");
        }
        pstmt.close();
        conectamos.close();
    }
    public Connection connection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
        String user = "root";
        String password = "123456";
        return DriverManager.getConnection(url,user,password);


    }

}
