<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Candidates Table</title>
  <link rel="stylesheet" href="style.css">
  <style>
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background-color: #f8f9fa;
      padding: 2rem;
    }

    h1 {
      text-align: center;
      color: #333;
    }

    table {
      width: 100%;
      max-width: 800px;
      margin: 2rem auto;
      border-collapse: collapse;
      box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
      background-color: white;
      border-radius: 8px;
      overflow: hidden;
    }
 
    th, td {
      padding: 1rem;
      text-align: left;
    }

    thead {
      background-color: #343a40;
      color: white;
    }

    tbody tr:nth-child(even) {
      background-color: #f1f3f5;
    }

    tbody tr:hover {
      background-color: #e9ecef;
    }
  </style>
</head>
<body>
  <h1>Voting Information Table</h1>
   <%@ page import="java.sql.*" %>
<% 
Class.forName("oracle.jdbc.driver.OracleDriver"); 
Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oracle");  
System.out.println("Connection Succeed with Database for showing candidates!"); 
Statement stmt = conn.createStatement(); 
ResultSet rs = stmt.executeQuery("SELECT CANDIDATE_ID , COUNT(VOTER_ID) FROM VOTING_TABLE GROUP BY CANDIDATE_ID");
 

%>
<table border="1">
<tr><th>Candidate  ID</th><th>Total Votes</th> </tr>
<%
while(rs.next()) {
%>
<tr>
 <tr>
  <td><%= rs.getInt("CANDIDATE_ID") %></td> 
  <td><%= rs.getString("COUNT(VOTER_ID)") %></td> 
</tr>

</tr>

<%
}
rs.close(); conn.close();
%>
</table>
   
  <a href="main.html" class="back-button">← Back to Menu</a>
</body>
</html>
