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
import java.sql.Timestamp;
import java.util.Date;

@WebServlet("/verifyVoter")
public class verifyVoter extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public verifyVoter() {
        super();
 
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter(); 
        int voterId =Integer.parseInt( request.getParameter("voterId"));
        String candidateId = request.getParameter("candidateID");
        
        String voterName=request.getParameter("voterName");
        out.print("<h2>Voter ID: "+ voterId+"</h2>");
        out.print("<h2>Voter Name: "+ voterName+"</h2>");  
        System.out.println("Candidate ID = " + candidateId);

        out.println("Candidate ID received: " + candidateId);
        try {
            // Load Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Timestamp timestamp = new Timestamp(new Date().getTime());
            // Establish connection
            Connection con = DriverManager.getConnection(
            	    "jdbc:oracle:thin:@localhost:1521/xe", "system", "oracle");
            System.out.println("✅ Connection Successful!");

//             Insert query
//            String query = "INSERT INTO VOTING_TABLE (BOOK_ID, BOOK_NAME, BOOK_AUTHOR,BOOK_PRICE) VALUES (?, ?, ?,?)";
            String query = "SELECT * FROM VOTER_TABLE WHERE VOTER_ID=? AND VOTER_NAME=?";
            PreparedStatement pst = con.prepareStatement(query); 
            pst.setInt(1, voterId);
            pst.setString(2, voterName.trim()); 
          
            int rows = pst.executeUpdate();
            if (rows > 0) {
                out.println("<p>✅ Data inserted successfully!</p>");  
                
                try {
                    // Load Oracle JDBC driver
                      

//                     Insert query
                    String query2 = "INSERT INTO VOTING_TABLE VALUES (?, ?, ?)"; 
                    PreparedStatement pst2 = con.prepareStatement(query2); 
                    pst2.setInt(1, Integer.parseInt(candidateId));
                    pst2.setInt(2,voterId); 
                    pst2.setTimestamp(3, timestamp);
                    int rows2 = pst2.executeUpdate();
                    if (rows2 > 0) {

                        String targetPage = "main.html"; 
                        response.sendRedirect(targetPage);
                        System.out.println("✅ " + rows + " Vote Registered.");
                    } else {

                    	response.sendRedirect("registerVoter.html");
                    }
         
                    pst.close();
                    con.close();
                } catch (Exception e) {
                    out.println("<p>❌ Error connecting to database: " + e.getMessage() + "</p>");
                    e.printStackTrace();
                }
//                String targetPage = "searchBook.html"; 
//                response.sendRedirect(targetPage);
//                System.out.println("✅ " + rows + " row(s) inserted.");
            } else {
//               out.println("<p>❌ Failed to insert data.</p>");
            	response.sendRedirect("validVoter.html");
            }
//
//            pst.close();
            con.close();
        } catch (Exception e) {
            out.println("<p>❌ Error connecting to database: " + e.getMessage() + "</p>");
            e.printStackTrace();
        }
        
        
        
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter(); 
        int voterId =Integer.parseInt( request.getParameter("voterId"));
        String voterName=request.getParameter("voterName");
        out.print("<h2>Voter ID: "+ voterId+"</h2>");
        out.print("<h2>Voter Name: "+ voterName+"</h2>");
        try {
            // Load Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Establish connection
            Connection con = DriverManager.getConnection(
            	    "jdbc:oracle:thin:@localhost:1521/xe", "system", "oracle");
            System.out.println("✅ Connection Successful!");

//             Insert query
            String query = "INSERT INTO VOTING_TABLE (BOOK_ID, BOOK_NAME, BOOK_AUTHOR,BOOK_PRICE) VALUES (?, ?, ?,?)";
//            PreparedStatement pst = con.prepareStatement(query); 
//            pst.setInt(1,Integer.parseInt(bookid));
//            pst.setString(2, bookname);
//            pst.setString(3, author);
//            pst.setInt(4, Integer.parseInt(bookprice));
//            
//            int rows = pst.executeUpdate();
//            if (rows > 0) {
//                pw.println("<p>✅ Data inserted successfully!</p>");  
//                String targetPage = "searchBook.html"; 
//                response.sendRedirect(targetPage);
//                System.out.println("✅ " + rows + " row(s) inserted.");
//            } else {
//                pw.println("<p>❌ Failed to insert data.</p>");
//            }
//
//            pst.close();
            con.close();
        } catch (Exception e) {
            out.println("<p>❌ Error connecting to database: " + e.getMessage() + "</p>");
            e.printStackTrace();
        }
		
	}

}
