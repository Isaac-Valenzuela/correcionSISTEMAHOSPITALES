import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class buscar extends JFrame {

    private JPanel panel1;
    private JTextField textField1;
    private JButton buscarButton;
    private JButton volverButton;

    public buscar(){
        setTitle("Buscar");
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
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    busqueda();
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
        });

    }

    public void busqueda()throws SQLException {
        int ci = Integer.parseInt(textField1.getText());
        Connection conectamos = connection();
        String sql = "Select * from PACIENTE where cedula = ?";
        PreparedStatement pstmt = conectamos.prepareStatement(sql);
        pstmt.setInt(1, ci);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            String cedula = rs.getString("cedula");
            String historial = rs.getString("n_historial_clinico");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String telefono = rs.getString("telefono");
            String edad = rs.getString("edad");
            String descripcion = rs.getString("descripcion_enfermedad");
            JOptionPane.showMessageDialog(null, "| Cedula: " +cedula +" | Historial Clinico: "
                    +historial +" | Nombre: " +nombre+" | Apellido: " +apellido+" | Telefono: " +telefono+" | Edad: "
                    + edad+ " | Descripcion de la enfermedad: " +descripcion);

        }
        rs.close();
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