package gr.uoa.tedi.backend.controller;

import gr.uoa.tedi.backend.model.User;
import gr.uoa.tedi.backend.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auctions/admin")
@CrossOrigin(origins = "http://localhost:4200") // allow frontend to call
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    // ✅ Get all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // ✅ Accept user (set accepted = true)
    @PutMapping("/users/{id}/accept")
    public ResponseEntity<User> acceptUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.acceptUser(id));
    }

    // ✅ Reject user (delete)
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
