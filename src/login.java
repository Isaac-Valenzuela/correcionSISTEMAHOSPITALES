import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login  extends JFrame{
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton INICIARSESIONButton;

    public login(){
        setTitle("Login");
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);

        INICIARSESIONButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    verficar();
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
        });

    }

    public void verficar() throws SQLException{
        String user = textField1.getText();
        String pass = textField2.getText();

        Connection conectamos = connection();
        String query="SELECT * FROM USUARIO WHERE username = ? AND password=?";
        PreparedStatement pstmt = conectamos.prepareStatement(query);
        pstmt.setString(1, user);
        pstmt.setString(2, pass);

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()){
            menu m = new menu();
            m.setVisible(true);
            dispose();

        }

    }
    public Connection connection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
        String user = "root";
        String password = "123456";
        return DriverManager.getConnection(url,user,password);
    }
}





