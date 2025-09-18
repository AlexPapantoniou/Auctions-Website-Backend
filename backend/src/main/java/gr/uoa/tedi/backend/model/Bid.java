package gr.uoa.tedi.backend.model;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bids")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bidid")
    private long bidid;

    @ManyToOne
    @JoinColumn(name = "auctionid", nullable = false)
    @JsonBackReference
    private Auction auction;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User bidder;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "time", columnDefinition = "TIMESTAMP(6)", nullable = false)
    private Instant time;

    public Bid() {
    }

    public Bid(Auction auction, User bidder, Double amount, Instant time) {
        this.auction = auction;
        this.bidder = bidder;
        this.amount = amount;
        this.time = time;
    }

    public long getBidId() {
        return bidid;
    }

    public void setBidId(Long bidid) {
        this.bidid = bidid;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public User getBidder() {
        return bidder;
    }

    public void setBidder(User bidder) {
        this.bidder = bidder;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

}
