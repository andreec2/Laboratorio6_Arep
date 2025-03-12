package com.example.demo.Controller;

import com.example.demo.LoginRequest;
import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import com.example.demo.UpdateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    // Crear usuario - POST /api/users
    @PostMapping
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        boolean success = userService.addUser(user);

        if (success) {
            response.put("message", "Usuario creado exitosamente");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            response.put("error", "El nombre de usuario ya existe");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    // Obtener usuario por username - GET /api/users/{username}
    @GetMapping("/{username}")
    public ResponseEntity<Object> getUser(@PathVariable String username) {
        User user = userService.getUser(username);
        if (user == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Usuario no encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Actualizar usuario - PUT /api/users/{username}
    @PutMapping("/{username}")
    public ResponseEntity<Map<String, Object>> updateUser(
            @PathVariable String username,
            @RequestBody UpdateUserRequest updateRequest) {

        Map<String, Object> response = new HashMap<>();
        boolean success = userService.updateUser(username, updateRequest);

        if (success) {
            response.put("message", "Usuario actualizado exitosamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("error", "Usuario no encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar usuario - DELETE /api/users/{username}
    @DeleteMapping("/{username}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable String username) {
        Map<String, Object> response = new HashMap<>();
        boolean success = userService.deleteUser(username);

        if (success) {
            response.put("message", "Usuario eliminado exitosamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("error", "Usuario no encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // Iniciar sesión - POST /api/users/login
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
        Map<String, Object> response = new HashMap<>();
        boolean authenticated = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        if (authenticated) {
            User user = userService.getUser(loginRequest.getUsername());
            response.put("message", "Inicio de sesión exitoso");
            response.put("role", user.getRole());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("error", "Credenciales inválidas");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    // Listar todos los usuarios - GET /api/users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}