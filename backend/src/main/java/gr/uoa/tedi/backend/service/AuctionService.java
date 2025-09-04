package gr.uoa.tedi.backend.service;

import java.util.ArrayList;
import java.util.List;

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

    public Auction getAuctionById(Long id) {
        return auctionRepository.findById(id).orElse(null);
    }

    public Auction registerAuction(Auction auction) {
        List<Category> itemCategories = new ArrayList<>();
        for (Category c : auction.getItem().getCategories()) {
            Category existing = categoryRepository.findByName(c.getName())
                    .orElseGet(() -> categoryRepository.save(c));

            itemCategories.add(existing);
        }

        auction.getItem().setCategories(itemCategories);
        return auctionRepository.save(auction);
    }
}
