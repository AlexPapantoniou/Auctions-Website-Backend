package gr.uoa.tedi.backend.repository;

import java.time.Instant;
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

        @Query("SELECT a FROM Auction a " +
                        "LEFT JOIN UserAuctionInteraction uai ON uai.auction = a AND uai.user.userid = :userid " +
                        "WHERE LOWER(a.item.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                        "LOWER(a.item.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
        Page<Auction> searchByKeyword(@Param("userid") Long userid, @Param("keyword") String keyword,
                        Pageable pageable);

        @Query("SELECT a FROM Auction a WHERE a.seller.userid = :sellerid")
        Page<Auction> findBySellerId(@Param("sellerid") Long sellerid, Pageable pageable);

        @Query("SELECT DISTINCT a.location FROM Auction a")
        List<String> findAllLocations();

        @Query("SELECT DISTINCT a.city FROM Auction a")
        List<String> findAllCities();

        @Query("SELECT DISTINCT a.country FROM Auction a")
        List<String> findAllCountries();

        @Query("SELECT MIN(a.currentBid) FROM Auction a")
        Double findMinPrice();

        @Query("SELECT MAX(a.currentBid) FROM Auction a")
        Double findMaxPrice();

        // Find the auctions considering all filters and sort them by weight
        @Query("SELECT DISTINCT a FROM Auction a " +
                        "LEFT JOIN UserAuctionInteraction uai ON uai.auction = a AND uai.user.userid = :userid " +
                        "LEFT JOIN a.item.categories c " +
                        "WHERE (:category = 'all' OR c.name = :category) " +
                        "AND (a.location = :location OR :location = 'all') " +
                        "AND (a.city = :city OR :city = 'all') " +
                        "AND (a.country = :country OR :country = 'all') " +
                        "AND (a.currentBid >= :minPrice AND a.currentBid <= :maxPrice) " +
                        "AND (a.active = true OR :activeOnly = false) " +
                        "GROUP BY a " +
                        "ORDER BY COALESCE(MAX(uai.weight), 0) DESC, a.startTime DESC")
        Page<Auction> findFilteredOrderedByWeight(
                        @Param("userid") Long userid,
                        @Param("category") String category,
                        @Param("location") String location,
                        @Param("city") String city,
                        @Param("country") String country,
                        @Param("minPrice") Double minPrice,
                        @Param("maxPrice") Double maxPrice,
                        @Param("activeOnly") boolean activeOnly,
                        Pageable pageable);

        @Query("SELECT a FROM Auction a WHERE a.startTime <= :currentTime AND a.endTime >= :currentTime AND a.active = false")
        List<Auction> findStartingAuctions(@Param("currentTime") Instant currentTime);

        @Query("SELECT a FROM Auction a WHERE a.endTime <= :currentTime AND a.active = true")
        List<Auction> findFinishedAuctions(@Param("currentTime") Instant currentTime);

        void deleteById(Long id);

}
