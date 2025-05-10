
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
 
@WebServlet("/registerCandidate")
public class registerCandidate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public registerCandidate() {
        super();
        DbConnect Db =new DbConnect();
        Db.createDBConnection();
    }

 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello from Servlet Candidate</h1>");
        int cid=Integer.parseInt(request.getParameter("cid"));
        String cname = request.getParameter("cname"); ;
        String cparty = request.getParameter("cparty");
        int cage =Integer.parseInt( request.getParameter("cage"));
        if (cage<=18) {
        	response.sendRedirect("error.html");
        }
        String cgender = request.getParameter("cgender"); 
 
        try {
            // Load Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Establish connection
            Connection con = DriverManager.getConnection(
            	    "jdbc:oracle:thin:@localhost:1521/xe", "system", "oracle");
            System.out.println("✅ Connection Successful for Inserting user!");

//             Insert query
            String query = "INSERT INTO CANDIDATE_TABLE VALUES (?, ?, ?,?,?)"; 
            PreparedStatement pst = con.prepareStatement(query); 
            pst.setInt(1, cid);
            pst.setString(2, cname.trim()); 
            pst.setString(3,cparty.trim());
            pst.setString(4, cgender.trim());
            pst.setInt(5, cage);
            int rows = pst.executeUpdate();
            if (rows > 0) {
                String targetPage = "main.html"; 
                response.sendRedirect(targetPage);
                System.out.println("✅ " + rows + " row(s) inserted.");
            } else {

            	response.sendRedirect("registerCandidate.html");
            }
 
            pst.close();
            con.close();
        } catch (Exception e) {
            out.println("<p>❌ Error connecting to database: " + e.getMessage() + "</p>");
            e.printStackTrace();
        }
        
	}

}
