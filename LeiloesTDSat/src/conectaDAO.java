
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {

    

    public Connection connectDB() {
        Connection conn = null;
        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exemplo_senac?useSSL=false", "root", "E9I)ejKPSfeo=");
            return conn;

        } catch (Exception e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            return conn;

        }

    }

}
