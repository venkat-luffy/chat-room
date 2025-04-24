package com.chatroom;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ChatServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try {
            Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM messages ORDER BY timestamp ASC");
            
            out.println("<html><body>");
            out.println("<h1>Chat Room</h1>");
            
            while (rs.next()) {
                out.println("<p><b>" + rs.getString("user") + ":</b> " + rs.getString("message") + "</p>");
            }
            
            out.println("<form method='POST'>");
            out.println("Name: <input type='text' name='user' required><br>");
            out.println("Message: <textarea name='message' required></textarea><br>");
            out.println("<input type='submit' value='Send'>");
            out.println("</form>");
            
            out.println("</body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String message = request.getParameter("message");
        
        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO messages (user, message) VALUES (?, ?)");
            stmt.setString(1, user);
            stmt.setString(2, message);
            stmt.executeUpdate();
            
            response.sendRedirect("chat");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
