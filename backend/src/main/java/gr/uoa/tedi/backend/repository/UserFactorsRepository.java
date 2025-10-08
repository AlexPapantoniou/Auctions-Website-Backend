package gr.uoa.tedi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gr.uoa.tedi.backend.model.UserFactors;

public interface UserFactorsRepository extends JpaRepository<UserFactors, Long> {

}