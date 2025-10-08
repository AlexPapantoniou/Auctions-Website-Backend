package gr.uoa.tedi.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.uoa.tedi.backend.model.UserAuctionInteraction;
import gr.uoa.tedi.backend.repository.UserAuctionInteractionRepository;

@Service
public class UserAuctionInteractionService {

    private final UserAuctionInteractionRepository userAuctionInteractionRepository;

    public UserAuctionInteractionService(UserAuctionInteractionRepository userAuctionInteractionRepository) {
        this.userAuctionInteractionRepository = userAuctionInteractionRepository;
    }

    @Transactional
    public UserAuctionInteraction logInteraction(UserAuctionInteraction uai) {
        Optional<UserAuctionInteraction> optOld = userAuctionInteractionRepository
                .findByUserUseridAndAuctionAuctionid(uai.getUser().getUserid(), uai.getAuction().getAuctionid());

        if (optOld.isPresent()) {
            UserAuctionInteraction old = optOld.get();
            old.setWeight(old.getWeight() + uai.getWeight());
            old.setInteractionType(uai.getInteractionType());

            return userAuctionInteractionRepository.save(old);
        } else {
            UserAuctionInteraction interaction = new UserAuctionInteraction();
            interaction.setUser(uai.getUser());
            interaction.setAuction(uai.getAuction());
            interaction.setInteractionType(uai.getInteractionType());
            interaction.setWeight(uai.getWeight());

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
