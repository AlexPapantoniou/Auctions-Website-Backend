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
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllExcludingAdmin());
    }

    // ✅ Accept user (set accepted = true)
    @PutMapping("/users/{userid}/accept")
    public ResponseEntity<User> acceptUser(@PathVariable Long userid) {
        return ResponseEntity.ok(userService.acceptUser(userid));
    }

    // ✅ Reject user (delete)
    @DeleteMapping("/users/{userid}/delete")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userid) {
        userService.deleteUser(userid);
        return ResponseEntity.noContent().build();
    }
}
