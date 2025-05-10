<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>User Table</title>
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
   <%@ page import="java.sql.*" %>
<% 
Class.forName("oracle.jdbc.driver.OracleDriver"); 
Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oracle");  
System.out.println("Connection Succeed with Database for showing Voters!"); 
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM VOTER_TABLE");
%>
<table border="1">
<tr><th>ID</th><th>Name</th><th>Age</th><th>Gender</th></tr>
<%
while(rs.next()) {
%>
<tr>
  <td><%= rs.getInt("VOTER_ID") %></td>
  <td><%= rs.getString("VOTER_NAME") %></td>
  <td><%= rs.getInt("VOTER_AGE") %></td>
  <td><%= rs.getString("VOTER_GENDER") %></td>
</tr>

<%
}
rs.close(); conn.close();
%>
</table>
   
  <a href="main.html" class="back-button">← Back to Menu</a>
</body>
</html>
