package com.example.session02.controller;

import com.example.session02.model.User;
import com.example.session02.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(
            @RequestParam(required = false) String search) {

        List<User> users = userService.findAllUsers();

        if (search != null && !search.isBlank()) {
            users = users.stream()
                    .filter(u -> u.getUsername()
                            .toLowerCase()
                            .contains(search.toLowerCase()))
                    .toList();
        }

        return ResponseEntity.ok(users);
    }
}
