import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class FabricaDeConexoes {

	public Connection getConnection() {
        try {
        	return DriverManager.getConnection("jdbc:mysql://localhost/doacao","root","123");
        } 
        catch (SQLException excecao) {
            throw new RuntimeException(excecao); //dispara uma exceção caso não consiga conectar ao banco
        }
    }
}