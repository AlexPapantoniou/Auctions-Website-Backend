package gr.uoa.tedi.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gr.uoa.tedi.backend.model.Auction;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

    @Query("SELECT a FROM Auction a WHERE " +
            "LOWER(a.item.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(a.item.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Auction> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT a FROM Auction a JOIN a.item.categories c WHERE c.name = :category")
    Page<Auction> findByCategory(@Param("category") String category, Pageable pageable);

}
