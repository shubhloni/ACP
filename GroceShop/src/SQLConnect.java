import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class SQLConnect 
{

	
	public static Connection dbConnector()
	{
		Connection conn=null;
		try
		{
			conn = DriverManager.getConnection("jdbc:mysql://localhost/groceshop", "root", "");
			//JOptionPane.showMessageDialog(null,"Connected");
			return conn;
		
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
	}

}
