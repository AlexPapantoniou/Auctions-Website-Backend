package gr.uoa.tedi.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "auction_factors")
public class AuctionFactors {

    @Id
    private Long auctionid;

    @OneToOne
    @MapsId
    @JoinColumn(name = "auction_id")
    private Auction auction;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String factors;

    public AuctionFactors() {
    }

    public AuctionFactors(Auction auction, String factors) {
        this.auction = auction;
        this.factors = factors;
    }

    public Long getAuctionid() {
        return auctionid;
    }

    public void setAuctionid(Long auctionid) {
        this.auctionid = auctionid;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public String getFactors() {
        return factors;
    }

    public void setFactors(String factors) {
        this.factors = factors;
    }

}
