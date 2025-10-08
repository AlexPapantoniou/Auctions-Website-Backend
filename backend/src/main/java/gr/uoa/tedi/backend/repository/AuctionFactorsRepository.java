package gr.uoa.tedi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gr.uoa.tedi.backend.model.AuctionFactors;

public interface AuctionFactorsRepository extends JpaRepository<AuctionFactors, Long> {

}