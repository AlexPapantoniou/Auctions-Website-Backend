package gr.uoa.tedi.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import gr.uoa.tedi.backend.model.Auction;
import gr.uoa.tedi.backend.model.User;
import gr.uoa.tedi.backend.model.UserAuctionInteraction;
import gr.uoa.tedi.backend.service.UserAuctionInteractionService;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/auctions/recommendations")
@CrossOrigin(origins = "https://localhost:4200")
public class RecommendationsController {

    private final UserAuctionInteractionService userAuctionInteractionService;

    public RecommendationsController(
            UserAuctionInteractionService userAuctionInteractionService) {
        this.userAuctionInteractionService = userAuctionInteractionService;
    }

    @PostMapping("/interactions")
    public UserAuctionInteraction logInteraction(
            @RequestParam Long userid,
            @RequestParam Long auctionid,
            @RequestParam String type,
            @RequestParam Double weight) {
        User user = new User();
        user.setUserid(userid);
        Auction auction = new Auction();
        auction.setAuctionid(auctionid);

        return userAuctionInteractionService.logInteraction(user, auction, type, weight);
    }

    @GetMapping("/interactions/user/{userid}")
    public List<UserAuctionInteraction> getUserInteractions(@PathVariable Long userid) {
        return userAuctionInteractionService.getInteractionsByUser(userid);
    }

    @GetMapping("/interactions/item/{auctionid}")
    public List<UserAuctionInteraction> getAuctionInteractions(@PathVariable Long auctionid) {
        return userAuctionInteractionService.getInteractionsByAuction(auctionid);
    }

}
