package gr.uoa.tedi.backend.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import gr.uoa.tedi.backend.model.Bid;
import gr.uoa.tedi.backend.repository.BidRepository;

@Service
public class BidService {

    private BidRepository bidRepository;

    BidService(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    public Page<Bid> getBidsByAuctionId(Long auctionId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("time").descending());
        return bidRepository.findByAuction_Auctionid(auctionId, pageable);
    }

    public Bid placeBid(Bid bid) {
        return bidRepository.save(bid);
    }
}
