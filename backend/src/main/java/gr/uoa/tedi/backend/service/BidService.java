package gr.uoa.tedi.backend.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import gr.uoa.tedi.backend.model.Auction;
import gr.uoa.tedi.backend.model.Bid;
import gr.uoa.tedi.backend.model.User;
import gr.uoa.tedi.backend.repository.AuctionRepository;
import gr.uoa.tedi.backend.repository.BidRepository;
import gr.uoa.tedi.backend.repository.UserRepository;

@Service
public class BidService {

    private BidRepository bidRepository;
    private AuctionRepository auctionRepository;
    private UserRepository userRepository;

    BidService(BidRepository bidRepository, AuctionRepository auctionRepository, UserRepository userRepository) {
        this.bidRepository = bidRepository;
        this.auctionRepository = auctionRepository;
        this.userRepository = userRepository;
    }

    public Page<Bid> getBidsByAuctionId(Long auctionId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("time").descending());
        return bidRepository.findByAuction_Auctionid(auctionId, pageable);
    }

    public Bid placeBid(Bid bid) {
        bid.setTime(Instant.now());

        Auction auction = auctionRepository.findById(bid.getAuction().getAuctionid())
                .orElseThrow(
                        () -> new RuntimeException("Auction not found with id " + bid.getAuction().getAuctionid()));

        if (!auction.isActive()) {
            throw new RuntimeException("Auction not currently active");
        }

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
        return bidRepository.save(bid);
    }
}
