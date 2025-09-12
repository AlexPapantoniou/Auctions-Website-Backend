package gr.uoa.tedi.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import gr.uoa.tedi.backend.model.Auction;
import gr.uoa.tedi.backend.model.Bid;
import gr.uoa.tedi.backend.model.User;
import gr.uoa.tedi.backend.repository.AuctionRepository;
import gr.uoa.tedi.backend.repository.UserRepository;
import gr.uoa.tedi.backend.service.BidService;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auctions")
@CrossOrigin(origins = "https://localhost:4200")
public class BidController {

    private final BidService bidService;
    private final AuctionRepository auctionRepository;
    private final UserRepository userRepository;

    BidController(BidService bidService, AuctionRepository auctionRepository, UserRepository userRepository) {
        this.bidService = bidService;
        this.auctionRepository = auctionRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/bids/auction/{auctionid}")
    public Page<Bid> getBidsByAuctionId(
            @PathVariable Long auctionid,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return bidService.getBidsByAuctionId(auctionid, page, size);
    }

    @PostMapping("/bids/place")
    public ResponseEntity<Bid> placeBid(@RequestBody Bid bid) {
        bid.setTime(LocalDateTime.now());

        Auction auction = auctionRepository.findById(bid.getAuction().getAuctionid())
                .orElseThrow(
                        () -> new RuntimeException("Auction not found with id " + bid.getAuction().getAuctionid()));

        User bidder = userRepository.findById(bid.getBidder().getUserid())
                .orElseThrow(
                        () -> new RuntimeException("User not found with id " + bid.getBidder().getUserid()));

        if (bid.getAmount() <= auction.getCurrentBid()) {
            throw new RuntimeException("Bid amount must be higher than current bid.");
        }

        bid.setAuction(auction);
        bid.setBidder(bidder);

        auction.setCurrentBid(bid.getAmount());
        auction.setNumberOfBids(auction.getNumberOfBids() + 1);

        auctionRepository.save(auction);
        Bid savedBid = bidService.placeBid(bid);

        return ResponseEntity.ok(savedBid);
    }

}
