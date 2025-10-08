package gr.uoa.tedi.backend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.uoa.tedi.backend.model.AuctionFactors;
import gr.uoa.tedi.backend.repository.AuctionFactorsRepository;

@Service
public class AuctionFactorsService {

    private final AuctionFactorsRepository auctionFactorsRepository;

    public AuctionFactorsService(AuctionFactorsRepository auctionFactorsRepository) {
        this.auctionFactorsRepository = auctionFactorsRepository;
    }

    @Transactional
    public AuctionFactors saveAuctionFactors(Long auctionid, String factors) {
        AuctionFactors auctionFactors = new AuctionFactors();
        auctionFactors.setAuctionid(auctionid);
        auctionFactors.setFactors(factors);

        return auctionFactorsRepository.save(auctionFactors);
    }

    public Optional<AuctionFactors> getAuctionFactors(Long auctionid) {
        return auctionFactorsRepository.findById(auctionid);
    }
}