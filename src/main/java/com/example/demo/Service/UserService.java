package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.UpdateUserRequest;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

@Service
public class UserService {
    private Map<String, User> userDatabase = new HashMap<>();

    public boolean addUser(User user) {
        if (userDatabase.containsKey(user.getUsername())) {
            return false;
        }
        userDatabase.put(user.getUsername(), user);
        return true;
    }

    public User getUser(String username) {
        return userDatabase.get(username);
    }

    public boolean updateUser(String username, UpdateUserRequest updateRequest) {
        User user = userDatabase.get(username);
        if (user == null) {
            return false;
        }

        if (updateRequest.getPassword() != null) {
            user.setPassword(updateRequest.getPassword());
        }

        if (updateRequest.getRole() != null) {
            user.setRole(updateRequest.getRole());
        }

        userDatabase.put(username, user);
        return true;
    }

    public boolean deleteUser(String username) {
        if (!userDatabase.containsKey(username)) {
            return false;
        }
        userDatabase.remove(username);
        return true;
    }

    public boolean authenticate(String username, String password) {
        User user = userDatabase.get(username);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userDatabase.values());
    }
}