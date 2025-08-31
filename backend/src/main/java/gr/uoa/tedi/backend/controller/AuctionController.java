package gr.uoa.tedi.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import gr.uoa.tedi.backend.model.Auction;
import gr.uoa.tedi.backend.service.AuctionService;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/auctions")
@CrossOrigin(origins = "http://localhost:4200")
public class AuctionController {

    private final AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping
    public Page<Auction> getAllAuctions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return auctionService.getAllAuctions(page, size);
    }

    @GetMapping("/search")
    public Page<Auction> searchAuctions(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return auctionService.searchAuctions(keyword, page, size);
    }

    @GetMapping("/category")
    public Page<Auction> searchAuctionsByCategory(
            @RequestParam String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return auctionService.searchAuctionsByCategory(category, page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auction> getAuctionById(@PathVariable Long id) {
        Auction auction = auctionService.getAuctionById(id);
        return auction != null ? ResponseEntity.ok(auction) : ResponseEntity.notFound().build();
    }

}
