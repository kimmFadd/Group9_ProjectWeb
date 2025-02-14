
package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.model.User;
import com.util.DBUtil;
import jakarta.servlet.annotation.MultipartConfig;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register/*"})
@MultipartConfig
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String role = request.getParameter("role"); // Retrieve role parameter
        
        // Hash password securely before storing (use proper hashing technique)
        // For simplicity, assuming plain text password storage (not recommended in production)
        String hashedPassword = password;

        User user = new User(username, hashedPassword, email, phone, role);

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            // Register JDBC driver (if not already registered)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Get connection to database
            conn = DBUtil.getConnection();

            // Prepare SQL statement for insertion
            String sql = "INSERT INTO Users (username, password, email, phone, role) VALUES (?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getRole()); // Set role parameter

            // Execute the statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                // Redirect to login page after successful registration
                response.sendRedirect("login.jsp");
            } else {
                response.getWriter().println("Failed to register user.");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            response.getWriter().println("Error: " + ex.getMessage());
        } finally {
            // Close resources
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
