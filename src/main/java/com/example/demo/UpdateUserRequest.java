package com.example.demo;

import com.example.demo.Model.User;

public class UpdateUserRequest {
    private String password;
    private User.Role role;

    // Getters y Setters
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User.Role getRole() {
        return role;
    }

    public void setRole(User.Role role) {
        this.role = role;
    }
}