package gr.uoa.tedi.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import gr.uoa.tedi.backend.model.User;
import gr.uoa.tedi.backend.repository.UserRepository;

@SpringBootApplication
public class AuctionsApplication {

	private final PasswordEncoder passwordEncoder;

	public AuctionsApplication(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(AuctionsApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
			// Check if admin exists
			if (userRepository.findByUsername("admin").isEmpty()) {
				User admin = new User();
				admin.setUsername("admin");
				admin.setPassword(passwordEncoder.encode("admin123"));
				admin.setFirstname("Admin");
				admin.setLastname("User");
				admin.setEmail("admin@example.com");
				admin.setAccepted(true);

				userRepository.save(admin);
			}
		};
	}

}
