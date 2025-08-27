package gr.uoa.tedi.backend.controller;

import gr.uoa.tedi.backend.model.User;
import gr.uoa.tedi.backend.service.UserService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auctions")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // @PostMapping("/login")
    // public User login(@RequestBody User loginRequest) {
    // return userService.login(loginRequest.getUsername(),
    // loginRequest.getPassword())
    // .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    // }
}