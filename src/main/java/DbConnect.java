import java.sql.DriverManager;
public class DbConnect {  

	public void createDBConnection() {
				 try { 
			            Class.forName("oracle.jdbc.driver.OracleDriver"); 
			            DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oracle");  
			            System.out.println("Connection Succeed with Database!");
			            
			        } catch (Exception e) { 
			            System.out.println(e);
			        }
			 }

		}
 