package gr.uoa.tedi.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

import gr.uoa.tedi.backend.model.Auction;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

    Optional<Auction> findById(Long id);

    // @Query("SELECT a FROM Auction a WHERE a.item.name = ?1")
    // Optional<Auction> findByItemName(String name);

}
