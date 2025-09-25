package gr.uoa.tedi.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gr.uoa.tedi.backend.model.Auction;
import gr.uoa.tedi.backend.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByAuctionOrderByTimestampAsc(Auction auction);

    @Query("SELECT COUNT(*) FROM Message m " +
            "WHERE m.auction.auctionid = :auctionid AND m.receiver.userid = :receiverid AND m.isRead = false")
    Long getUnreadMessagesCount(@Param("auctionid") Long auctionid, @Param("receiverid") Long receiverid);
}
