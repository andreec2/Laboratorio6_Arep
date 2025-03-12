package com.example.demo.Model;

public class User {
    private String username;
    private String password;
    private Role role;

    public enum Role {
        ADMIN, USER
    }

    public User() {} // Constructor vacío necesario para Spring

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters y Setters (mismos que antes)
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
