package gr.uoa.tedi.backend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.uoa.tedi.backend.model.UserFactors;
import gr.uoa.tedi.backend.repository.UserFactorsRepository;

@Service
public class UserFactorsService {

    private final UserFactorsRepository userFactorsRepository;

    public UserFactorsService(UserFactorsRepository userFactorsRepository) {
        this.userFactorsRepository = userFactorsRepository;
    }

    @Transactional
    public UserFactors saveUserFactors(Long userid, String factors) {
        UserFactors userFactors = new UserFactors();
        userFactors.setUserid(userid);
        userFactors.setFactors(factors);

        return userFactorsRepository.save(userFactors);
    }

    public Optional<UserFactors> getUserFactors(Long userid) {
        return userFactorsRepository.findById(userid);
    }
}
