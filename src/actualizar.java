import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class actualizar extends JFrame {

    private JPanel panel1;
    private JTextField textField1; // Cedula
    private JTextField textField2; // Telefono
    private JTextField textField4; // Edad
    private JTextField textField3; // Descripcion
    private JButton actualizarButton;
    private JButton volverButton;

    public actualizar() {
        setTitle("Actualizar");
        setSize(400, 400);
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

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    actualizarDatos();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void actualizarDatos() throws SQLException {
        String cedula = textField1.getText();
        String telefono = textField2.getText();
        String edad = textField4.getText();
        String descripcion = textField3.getText();

        Connection conectamos = connection();
        String sql = "UPDATE PACIENTE SET telefono = ?, edad = ?, descripcion_enfermedad = ? WHERE cedula = ?";
        PreparedStatement pstmt = conectamos.prepareStatement(sql);
        pstmt.setString(1, telefono);
        pstmt.setInt(2, Integer.parseInt(edad));
        pstmt.setString(3, descripcion);
        pstmt.setString(4, cedula);

        int rowAffected = pstmt.executeUpdate();
        if (rowAffected > 0) {
            JOptionPane.showMessageDialog(null, "Registro actualizado correctamente");
        }
        pstmt.close();
        conectamos.close();
    }

    public Connection connection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
        String user = "root";
        String password = "123456";
        return DriverManager.getConnection(url, user, password);
    }

}
