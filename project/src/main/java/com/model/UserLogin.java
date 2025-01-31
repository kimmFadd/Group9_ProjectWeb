package com.model;

import java.io.Serializable;

public class UserLogin implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String role;
    private String email;
    // Add any additional fields as needed

    public UserLogin() {
        // Default constructor
    }

    // Constructor with all fields
    public UserLogin(String username, String password, String role, String email) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        // Initialize other fields if needed
    }

    // Getters and setters for all fields

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
