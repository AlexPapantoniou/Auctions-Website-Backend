package gr.uoa.tedi.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import gr.uoa.tedi.backend.model.User;
import gr.uoa.tedi.backend.repository.UserRepository;

@SpringBootApplication
public class AuctionsApplication {

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
				admin.setPassword("admin123"); // ideally hash this
				admin.setFirstname("Admin");
				admin.setLastname("User");
				admin.setEmail("admin@example.com");
				admin.setAccepted(true);

				userRepository.save(admin);
			}
		};
	}

}
