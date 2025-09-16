package gr.uoa.tedi.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gr.uoa.tedi.backend.model.Auction;
import gr.uoa.tedi.backend.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByAuctionOrderByTimestampAsc(Auction auction);
}
