<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Voting Panel</title>
     <link rel="stylesheet" href="style.css">
    <style>
        body {
            font-family: Arial;
            background-color: #f4f4f4;
            padding: 30px;
        }
        table {
            width: 60%;
            margin: auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 0 10px #ccc;
        }
        th, td {
            padding: 12px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #2e8b57;
            color: white;
        }
        form {
            margin: 0;
        }
        .vote-btn {
            padding: 8px 16px;
            background-color: #28a745;
            border: none;
            color: white;
            cursor: pointer;
            border-radius: 5px;
        }
        .vote-btn:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <h2 align="center">Voting Panel</h2>

<%
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver"); 
        conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oracle");  
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM CANDIDATE_TABLE");
%>

    <table>
        <tr>
            <th>ID</th>
            <th>Party Name</th>
            <th>Candidate Name</th>
            <th>Vote</th>
        </tr>

        <% while(rs.next()) { %>
        <tr>
            <td><%= rs.getInt("CANDIDATE_ID") %></td>
            <td><%= rs.getString("PARTY_NAME") %></td> 
            <td><%= rs.getString("CANDIDATE_NAME") %></td>
            <td>
                <form action="giveVote" method="post">
                    <input type="hidden" name="candidate_id" value="<%= rs.getInt("CANDIDATE_ID") %>">
                    <input type="hidden" name="candidate_name" value="<%= rs.getString("CANDIDATE_name") %>">
                    <input type="hidden" name="partyname" value="<%= rs.getString("PARTY_NAME") %>"> 
                    <button class="vote-btn" type="submit">Vote</button>
                </form>
            </td>
        </tr>
        <% } %>

    

<%
    } catch(Exception e) {
        out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
    } finally {
        if(rs != null) try { rs.close(); } catch(Exception e) {}
        if(stmt != null) try { stmt.close(); } catch(Exception e) {}
        if(conn != null) try { conn.close(); } catch(Exception e) {}
    }
%>
</table><br><a href="main.html" class="back-button">← Back to Home</a>
</body>
</html>
