package gr.uoa.tedi.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import gr.uoa.tedi.backend.model.AuctionFactors;
import gr.uoa.tedi.backend.model.UserAuctionInteraction;
import gr.uoa.tedi.backend.model.UserFactors;
import gr.uoa.tedi.backend.service.AuctionFactorsService;
import gr.uoa.tedi.backend.service.UserFactorsService;
import gr.uoa.tedi.backend.service.UserAuctionInteractionService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/auctions/recommendations")
@CrossOrigin(origins = "https://localhost:4200")
public class RecommendationsController {

    private final UserAuctionInteractionService userAuctionInteractionService;
    private final UserFactorsService userFactorsService;
    private final AuctionFactorsService auctionFactorsService;

    public RecommendationsController(
            UserAuctionInteractionService userAuctionInteractionService,
            UserFactorsService userFactorsService,
            AuctionFactorsService auctionFactorsService) {
        this.userAuctionInteractionService = userAuctionInteractionService;
        this.userFactorsService = userFactorsService;
        this.auctionFactorsService = auctionFactorsService;
    }

    @PostMapping("/interactions")
    public ResponseEntity<UserAuctionInteraction> logInteraction(@RequestBody UserAuctionInteraction uai) {
        return ResponseEntity.ok(userAuctionInteractionService.logInteraction(uai));
    }

    @GetMapping("/interactions/user/{userid}")
    public List<UserAuctionInteraction> getUserInteractions(@PathVariable Long userid) {
        return userAuctionInteractionService.getInteractionsByUser(userid);
    }

    @GetMapping("/interactions/item/{auctionid}")
    public List<UserAuctionInteraction> getAuctionInteractions(@PathVariable Long auctionid) {
        return userAuctionInteractionService.getInteractionsByAuction(auctionid);
    }

    // User factors
    @PostMapping("/factors/user/{userid}")
    public UserFactors saveUserFactors(
            @PathVariable Long userid,
            @RequestBody String factors) {
        return userFactorsService.saveUserFactors(userid, factors);
    }

    @GetMapping("/factors/user/{userid}")
    public UserFactors getUserFactors(@PathVariable Long userid) {
        return userFactorsService.getUserFactors(userid).orElse(null);
    }

    // Auction factors
    @PostMapping("/factors/auction/{auctionid}")
    public AuctionFactors saveAuctionFactors(
            @PathVariable Long auctionid,
            @RequestBody String factors) {
        return auctionFactorsService.saveAuctionFactors(auctionid, factors);
    }

    @GetMapping("/factors/auction/{auctionid}")
    public AuctionFactors getAuctionFactors(@PathVariable Long auctionid) {
        return auctionFactorsService.getAuctionFactors(auctionid).orElse(null);
    }

}