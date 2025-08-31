package gr.uoa.tedi.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import gr.uoa.tedi.backend.model.Auction;
import gr.uoa.tedi.backend.repository.AuctionRepository;

@Service
public class AuctionService {

    private final AuctionRepository auctionRepository;

    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
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
}
