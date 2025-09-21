package gr.uoa.tedi.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gr.uoa.tedi.backend.model.UserAuctionInteraction;

public interface UserAuctionInteractionRepository extends JpaRepository<UserAuctionInteraction, Long> {
    List<UserAuctionInteraction> findByUserUserid(Long userid);

    List<UserAuctionInteraction> findByAuctionAuctionid(Long auctionid);

    Optional<UserAuctionInteraction> findByUserUseridAndAuctionAuctionid(Long userid, Long auctionid);
}
