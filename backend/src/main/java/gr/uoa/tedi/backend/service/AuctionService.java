package gr.uoa.tedi.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import gr.uoa.tedi.backend.model.Category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import gr.uoa.tedi.backend.model.Auction;
import gr.uoa.tedi.backend.repository.AuctionRepository;
import gr.uoa.tedi.backend.repository.CategoryRepository;

@Service
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final CategoryRepository categoryRepository;

    public AuctionService(
            AuctionRepository auctionRepository,
            CategoryRepository categoryRepository) {
        this.auctionRepository = auctionRepository;
        this.categoryRepository = categoryRepository;
    }

    public Page<Auction> getAllAuctions(int page, int size) {
        return auctionRepository.findAll(PageRequest.of(page, size));
    }

    public Page<Auction> searchAuctions(String keyword, int page, int size) {
        return auctionRepository.searchByKeyword(keyword, PageRequest.of(page, size));
    }

    public Page<Auction> searchAuctionsByCategory(String category, int page, int size) {
        return auctionRepository.findByCategory(category, PageRequest.of(page, size));
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

    public Page<Auction> getAuctionsByLocation(String location, int page, int size) {
        return auctionRepository.findByLocation(location, PageRequest.of(page, size));
    }

    public Page<Auction> getAuctionsByCity(String city, int page, int size) {
        return auctionRepository.findByCity(city, PageRequest.of(page, size));
    }

    public Page<Auction> getAuctionsByCountry(String country, int page, int size) {
        return auctionRepository.findByCountry(country, PageRequest.of(page, size));
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
            existingAuction.setbuyPrice(updatedAuction.getbuyPrice());
            existingAuction.setFirstBid(updatedAuction.getFirstBid());
            existingAuction.setStartTime(updatedAuction.getStartTime());
            existingAuction.setEndTime(updatedAuction.getEndTime());
            existingAuction.getItem().setDescription(updatedAuction.getItem().getDescription());
            existingAuction.setCity(updatedAuction.getCity());
            existingAuction.setCountry(updatedAuction.getCountry());

            return auctionRepository.save(existingAuction);
        });
    }

    public void deleteAuction(Long auctionid) {
        auctionRepository.deleteById(auctionid);
    }
}
