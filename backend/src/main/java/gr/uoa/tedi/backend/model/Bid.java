package gr.uoa.tedi.backend.model;

import java.time.LocalDateTime;

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
    private Auction auction;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User bidder;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    public Bid() {
    }

    public Bid(Auction auction, User bidder, double amount, LocalDateTime time) {
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

}
