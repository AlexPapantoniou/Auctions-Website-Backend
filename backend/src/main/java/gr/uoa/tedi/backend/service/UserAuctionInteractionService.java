package gr.uoa.tedi.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.uoa.tedi.backend.model.Auction;
import gr.uoa.tedi.backend.model.User;
import gr.uoa.tedi.backend.model.UserAuctionInteraction;
import gr.uoa.tedi.backend.repository.UserAuctionInteractionRepository;

@Service
public class UserAuctionInteractionService {

    private final UserAuctionInteractionRepository userAuctionInteractionRepository;

    public UserAuctionInteractionService(UserAuctionInteractionRepository userAuctionInteractionRepository) {
        this.userAuctionInteractionRepository = userAuctionInteractionRepository;
    }

    @Transactional
    public UserAuctionInteraction logInteraction(User user, Auction auction, String type, Double weight) {
        Optional<UserAuctionInteraction> optOld = userAuctionInteractionRepository
                .findByUserUseridAndAuctionAuctionid(user.getUserid(), auction.getAuctionid());
        if (optOld.isPresent()) {
            UserAuctionInteraction old = optOld.get();
            old.setWeight(old.getWeight() + weight);
            return userAuctionInteractionRepository.save(old);
        } else {
            UserAuctionInteraction interaction = new UserAuctionInteraction();
            interaction.setUser(user);
            interaction.setAuction(auction);
            interaction.setInteractionType(type);
            interaction.setWeight(weight);

            return userAuctionInteractionRepository.save(interaction);
        }
    }

    public List<UserAuctionInteraction> getInteractionsByUser(Long userid) {
        return userAuctionInteractionRepository.findByUserUserid(userid);
    }

    public List<UserAuctionInteraction> getInteractionsByAuction(Long auctionid) {
        return userAuctionInteractionRepository.findByAuctionAuctionid(auctionid);
    }
}
