package gr.uoa.tedi.backend.service;

import java.io.IOException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import gr.uoa.tedi.backend.model.Auction;
import gr.uoa.tedi.backend.model.export.AuctionExport;
import gr.uoa.tedi.backend.model.export.AuctionsExport;
import gr.uoa.tedi.backend.model.export.BidExport;
import gr.uoa.tedi.backend.model.export.BidderExport;
import gr.uoa.tedi.backend.model.export.BidsExport;
import gr.uoa.tedi.backend.model.export.SellerExport;
import gr.uoa.tedi.backend.repository.AuctionRepository;

@Service
public class ExportService {

    private final AuctionRepository auctionRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
            .withZone(ZoneId.systemDefault());

    public ExportService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    public AuctionExport mapAuctionToItemExport(Auction auction) {
        AuctionExport auctionExport = new AuctionExport();

        auctionExport.setItemID(auction.getAuctionid());
        auctionExport.setName(auction.getItem().getName());

        auctionExport.setCategories(auction.getItem().getCategories()
                .stream()
                .map(c -> c.getName())
                .toList());
        auctionExport.setCurrently("$" + auction.getCurrentBid());
        auctionExport.setBuyPrice(auction.getBuyPrice() != null ? "$" + auction.getBuyPrice() : null);
        auctionExport.setFirstBid("$" + auction.getFirstBid());
        auctionExport.setNumberOfBids(auction.getNumberOfBids());

        List<BidExport> bidExports = auction.getBids().stream().map(bid -> {
            BidderExport bidderExport = new BidderExport();
            bidderExport.setRating(bid.getBidder().getBidderRating());
            bidderExport.setUserID(bid.getBidder().getUsername());
            bidderExport.setLocation(bid.getBidder().getCity());
            bidderExport.setCountry(bid.getBidder().getCountry());

            BidExport bidExport = new BidExport();
            bidExport.setBidder(bidderExport);
            bidExport.setTime(FORMATTER.format(bid.getTime()));
            bidExport.setAmount("$" + bid.getAmount());

            return bidExport;
        }).toList();

        BidsExport bidsExport = new BidsExport();
        bidsExport.setBids(bidExports);
        auctionExport.setBids(bidsExport);

        auctionExport.setLocation(auction.getAddress() + ", " + auction.getLocation() + ", " + auction.getCity());
        auctionExport.setCountry(auction.getCountry());
        auctionExport.setStarted(FORMATTER.format(auction.getStartTime()));
        auctionExport.setEnds(FORMATTER.format(auction.getEndTime()));

        SellerExport sellerExport = new SellerExport();
        sellerExport.setRating(auction.getSeller().getSellerRating());
        sellerExport.setUserID(auction.getSeller().getUsername());
        auctionExport.setSeller(sellerExport);

        auctionExport.setDescription(auction.getItem().getDescription());

        return auctionExport;
    }

    public AuctionsExport exportAuctionsAsXml() {
        List<AuctionExport> auctions = auctionRepository.findAll().stream()
                .map(auction -> mapAuctionToItemExport(auction))
                .collect(Collectors.toList());

        return new AuctionsExport(auctions);
    }

    public String exportAuctionsAsJSON(List<AuctionExport> auctions) throws IOException {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(auctions);
    }

}
