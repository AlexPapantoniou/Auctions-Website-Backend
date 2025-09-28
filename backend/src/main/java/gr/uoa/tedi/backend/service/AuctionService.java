package gr.uoa.tedi.backend.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import gr.uoa.tedi.backend.model.Category;
import gr.uoa.tedi.backend.model.User;
import gr.uoa.tedi.backend.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.uoa.tedi.backend.model.Auction;
import gr.uoa.tedi.backend.model.Bid;
import gr.uoa.tedi.backend.repository.AuctionRepository;
import gr.uoa.tedi.backend.repository.CategoryRepository;

@Service
public class AuctionService {

    private final UserRepository userRepository;

    private final AuctionRepository auctionRepository;
    private final CategoryRepository categoryRepository;

    public AuctionService(
            AuctionRepository auctionRepository,
            CategoryRepository categoryRepository, UserRepository userRepository) {
        this.auctionRepository = auctionRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public Page<Auction> searchAuctions(Long userid, String keyword, int page, int size) {
        return auctionRepository.searchByKeyword(userid, keyword, PageRequest.of(page, size));
    }

    public Page<Auction> getAuctionsBySeller(Long sellerid, int page, int size) {
        return auctionRepository.findBySellerId(sellerid, PageRequest.of(page, size));
    }

    public Auction getAuctionById(Long id) {
        return auctionRepository.findById(id).orElse(null);
    }

    public List<String> getAllLocations() {
        return auctionRepository.findAllLocations();
    }

    public List<String> getAllCities() {
        return auctionRepository.findAllCities();
    }

    public List<String> getAllCountries() {
        return auctionRepository.findAllCountries();
    }

    public Double getMinPrice() {
        return auctionRepository.findMinPrice();
    }

    public Double getMaxPrice() {
        return auctionRepository.findMaxPrice();
    }

    public Page<Auction> getAuctionsFilteredOrderedByWeight(Long userid, String category, String location,
            String city, String country, Double minPrice, Double maxPrice, boolean activeOnly, int page, int size) {

        return auctionRepository.findFilteredOrderedByWeight(userid, category, location, city, country, minPrice,
                maxPrice, activeOnly, PageRequest.of(page, size));
    }

    public Auction registerAuction(Auction auction) {
        auction.setCurrentBid(auction.getFirstBid());
        List<Category> itemCategories = new ArrayList<>();
        for (Category c : auction.getItem().getCategories()) {
            Category existing = categoryRepository.findByName(c.getName())
                    .orElseGet(() -> categoryRepository.save(c));

            itemCategories.add(existing);
        }

        auction.getItem().setCategories(itemCategories);
        return auctionRepository.save(auction);
    }

    public Optional<Auction> updateAuction(Long auctionId, Auction updatedAuction) {
        return auctionRepository.findById(auctionId).map(existingAuction -> {
            existingAuction.setBuyPrice(updatedAuction.getBuyPrice());
            existingAuction.setFirstBid(updatedAuction.getFirstBid());
            existingAuction.setStartTime(updatedAuction.getStartTime());
            existingAuction.setEndTime(updatedAuction.getEndTime());
            existingAuction.getItem().setDescription(updatedAuction.getItem().getDescription());
            existingAuction.setCity(updatedAuction.getCity());
            existingAuction.setCountry(updatedAuction.getCountry());

            return auctionRepository.save(existingAuction);
        });
    }

    @Transactional
    public void activateAuctions() {
        List<Auction> startingAuctions = auctionRepository.findStartingAuctions(Instant.now());

        for (Auction auction : startingAuctions) {
            auction.setActive(true);
            auctionRepository.save(auction);
        }
    }

    @Transactional
    public void closeExpiredAuctions() {
        List<Auction> expiredAuctions = auctionRepository.findFinishedAuctions(Instant.now());

        for (Auction auction : expiredAuctions) {
            auction.setActive(false);

            Bid winningBid = auction.getBids().stream()
                    .max(Comparator.comparingDouble(Bid::getAmount))
                    .orElse(null);

            if (winningBid != null) {
                User winner = winningBid.getBidder();
                auction.setWinner(winner);

                winner.setBidderRating(winner.getBidderRating() + (int) (winningBid.getAmount() * 1));
                userRepository.save(winner);

                User seller = auction.getSeller();
                seller.setSellerRating(seller.getSellerRating() + (int) (winningBid.getAmount() * 1));
                userRepository.save(seller);
            }

            auctionRepository.save(auction);
        }
    }

    public Auction buyNow(Long auctionId, Long bidderId) {
        Auction auction = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new RuntimeException("Auction not found"));

        if (!auction.isActive()) {
            throw new RuntimeException("Auction is not currently active");
        }

        if (auction.getBuyPrice() == null) {
            throw new RuntimeException("This auction does not support 'Buy Now'");
        }

        User bidder = userRepository.findById(bidderId)
                .orElseThrow(() -> new RuntimeException("Bidder not found"));

        auction.setActive(false);
        auction.setEndTime(Instant.now());
        auction.setWinner(bidder);

        bidder.setBidderRating(bidder.getBidderRating() + (int) (auction.getBuyPrice() * 1));
        userRepository.save(bidder);

        User seller = auction.getSeller();
        seller.setSellerRating(seller.getSellerRating() + (int) (auction.getBuyPrice() * 1));
        userRepository.save(seller);

        return auctionRepository.save(auction);
    }

    public void deleteAuction(Long auctionid) {
        auctionRepository.deleteById(auctionid);
    }

}
