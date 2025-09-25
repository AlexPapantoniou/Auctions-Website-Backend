package gr.uoa.tedi.backend.service;

import gr.uoa.tedi.backend.model.User;
import gr.uoa.tedi.backend.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userid) {
        return userRepository.findById(userid);
    }

    public List<User> getAllExcludingAdmin() {
        return userRepository.findAllExcludingAdmin();
    }

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAccepted(false);
        return userRepository.save(user);
    }

    public User login(String username, String rawPassword) {
        Optional<User> otpUser = userRepository.findByUsername(username);
        if (otpUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = otpUser.get();

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        if (!user.isAccepted()) {
            throw new RuntimeException("User not yet accepted by the admin");
        }

        return user;
    }

    public User acceptUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setAccepted(true);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}