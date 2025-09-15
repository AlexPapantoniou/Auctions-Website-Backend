package gr.uoa.tedi.backend.repository;

import java.time.LocalDateTime;
import java.util.List;

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

    @Query("SELECT a FROM Auction a WHERE a.seller.userid = :sellerid")
    Page<Auction> findBySellerId(@Param("sellerid") Long sellerid, Pageable pageable);

    @Query("SELECT DISTINCT a.location FROM Auction a")
    List<String> findAllLocations();

    @Query("SELECT DISTINCT a.city FROM Auction a")
    List<String> findAllCities();

    @Query("SELECT DISTINCT a.country FROM Auction a")
    List<String> findAllCountries();

    @Query("SELECT a FROM Auction a WHERE a.location = :location")
    Page<Auction> findByLocation(String location, Pageable pageable);

    @Query("SELECT a FROM Auction a WHERE a.city = :city")
    Page<Auction> findByCity(String city, Pageable pageable);

    @Query("SELECT a FROM Auction a WHERE a.country = :country")
    Page<Auction> findByCountry(String country, Pageable pageable);

    List<Auction> findByEndTimeBeforeAndActiveIsTrue(LocalDateTime currentTime);

    void deleteById(Long id);

}
