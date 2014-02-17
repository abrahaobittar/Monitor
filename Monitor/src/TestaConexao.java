import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {

		Connection conexao = new FabricaDeConexoes().getConnection();
        JOptionPane.showMessageDialog(null,"Conexao com banco","Mensagem",
                JOptionPane.INFORMATION_MESSAGE);
        
        conexao.close();
	}
}
