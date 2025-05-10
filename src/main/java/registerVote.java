 import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
 
@WebServlet("/registerVote")
public class registerVote extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public registerVote() {
        super(); 
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
//        out.println("<h1>Hello from Servlet Voting</h1>"); 
        String c_id =request.getParameter("candidate_id");
        String c_name=request.getParameter("candidate_name");
        String party=request.getParameter("partyname");
//        out.print("<h2>Candidate id: "+c_id+" </h2>"); 
//        out.print("<h2>Candidate Name: "+c_name+" </h2>");
//        out.print("<h2>Candidate Party  Name: "+party+" </h2>");
        out.println("<html>");
        out.println("<head><title>Voter Information</title>");
        out.println(" <link rel=\"stylesheet\" href=\"style.css\">");
        // Add CSS Styling
        out.println("<style>");
        out.println("body {font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 50px;}");
        out.println("h2 {text-align: center; color: #2e8b57;}");
        out.println("form {width: 300px; margin: 0 auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);}");
        out.println("label {font-size: 14px; color: #333333; margin-bottom: 8px; display: block;}");
        out.println("input[type='text'] {width: 100%; padding: 10px; margin-bottom: 20px; border: 1px solid #ddd; border-radius: 4px;}");
        out.println("button {background-color: #28a745; color: white; border: none; padding: 10px 20px; border-radius: 5px; font-size: 16px; cursor: pointer; width: 100%;}");
        out.println("button:hover {background-color: #218838;}");
        out.println("</style>");
        
        out.println("</head>");
        out.println("<body>");
        
        // Heading
        out.println("<h2>Enter Your Voter Information</h2>");
        
        // Voter ID and Name form
        out.println("<form action='verifyVoter' method='get'>");
        out.println("<label for='voterId'>Voter ID:</label>");
        out.println("<input type='text' id='voterId' name='voterId' required><br>");
        
        out.println("<label for='voterName'>Voter Name:</label>");
        out.println("<input type='text' id='voterName' name='voterName' required><br>");
         
        out.println("<input type='hidden' name='candidateID' value='" + c_id + "' readonly><br>");
        

        
        out.println("<button type='submit'>Submit Vote</button>");
        out.println("</form>");
        
        out.println("</body>");
        out.println("</html>");
	}

}
