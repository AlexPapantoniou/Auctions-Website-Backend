package gr.uoa.tedi.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import gr.uoa.tedi.backend.model.Bid;
import gr.uoa.tedi.backend.service.BidService;

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

    BidController(BidService bidService) {
        this.bidService = bidService;
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
        Bid savedBid = bidService.placeBid(bid);
        return ResponseEntity.ok(savedBid);
    }

}
