package gr.uoa.tedi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import gr.uoa.tedi.backend.model.Bid;

public interface BidRepository extends JpaRepository<Bid, Long> {
    Page<Bid> findByAuction_Auctionid(Long auctionid, Pageable pageable);
}
