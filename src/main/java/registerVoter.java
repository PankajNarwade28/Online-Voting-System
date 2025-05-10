
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/registerVoter")
public class registerVoter extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public registerVoter() {
    		super();
    		DbConnect db=new DbConnect();
    		db.createDBConnection();
 
    }
    
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello from Servlet Voter</h1>");
        String vname = request.getParameter("vname");
        int vage =Integer.parseInt( request.getParameter("vage"));
        if (vage<=18) {
        	response.sendRedirect("error.html");
        }
        String vgender = request.getParameter("vgender");
        int vid=Integer.parseInt(request.getParameter("vid"));
//        out.print("<h2>Voter name: "+vname+" </h2>");
//        out.print("<h2>Voter Age: "+vage+" </h2>");
//        out.print("<h2>Voter Gender: "+vgender+" </h2>");
        try {
            // Load Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Establish connection
            Connection con = DriverManager.getConnection(
            	    "jdbc:oracle:thin:@localhost:1521/xe", "system", "oracle");
            System.out.println("✅ Connection Successful for Inserting user!");

//             Insert query
            String query = "INSERT INTO VOTER_TABLE VALUES (?, ?, ?,?)"; 
            PreparedStatement pst = con.prepareStatement(query); 
            pst.setInt(1, vid);
            pst.setString(2, vname.trim()); 
            pst.setString(3,vgender);
            pst.setInt(4, vage);            
            int rows = pst.executeUpdate();
            if (rows > 0) {

                String targetPage = "main.html"; 
                response.sendRedirect(targetPage);
                System.out.println("✅ " + rows + " row(s) inserted.");
            } else {

            	response.sendRedirect("registerVoter.html");
            }
 
            pst.close();
            con.close();
        } catch (Exception e) {
            out.println("<p>❌ Error connecting to database: " + e.getMessage() + "</p>");
            e.printStackTrace();
        }
        
        
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
