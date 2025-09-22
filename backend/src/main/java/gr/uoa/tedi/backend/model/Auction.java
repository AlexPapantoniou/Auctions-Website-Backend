package gr.uoa.tedi.backend.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "auctions")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auctionid")
    private Long auctionid;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User seller;

    @ManyToOne
    private User winner;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "itemid", referencedColumnName = "itemid", nullable = false)
    private Item item;

    @Column(name = "firstbid")
    private Double firstBid;

    @Column(name = "currentbid")
    private Double currentBid;

    @Column(name = "buyprice")
    private Double buyPrice;

    @Column(name = "address")
    private String address;

    @Column(name = "location")
    private String location;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "numberofbids")
    private Integer numberOfBids = 0;

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Bid> bids = new ArrayList<>();

    @Column(name = "starttime", columnDefinition = "TIMESTAMP(6)")
    private Instant startTime;

    @Column(name = "endtime", columnDefinition = "TIMESTAMP(6)")
    private Instant endTime;

    @Column(name = "active")
    private Boolean active = false;

    @OneToMany(mappedBy = "auction")
    private List<Message> messages = new ArrayList<>();

    public Auction() {
    }

    public Auction(User seller, Item item, Double firstBid, Double buyPrice, Instant startTime,
            Instant endTime, String address, String location, String city, String country) {
        this.seller = seller;
        this.item = item;
        this.firstBid = firstBid;
        this.currentBid = firstBid;
        this.buyPrice = buyPrice;
        this.numberOfBids = 0;
        this.startTime = startTime;
        this.endTime = endTime;
        this.address = address;
        this.location = location;
        this.city = city;
        this.country = country;
        this.active = false;
    }

    public Long getAuctionid() {
        return auctionid;
    }

    public void setAuctionid(Long auctionid) {
        this.auctionid = auctionid;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public User getWinner() {
        return winner;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Double getFirstBid() {
        return firstBid;
    }

    public void setFirstBid(Double firstBid) {
        this.firstBid = firstBid;
    }

    public Double getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(Double currentBid) {
        this.currentBid = currentBid;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Integer getNumberOfBids() {
        return numberOfBids;
    }

    public void setNumberOfBids(Integer numberOfBids) {
        this.numberOfBids = numberOfBids;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}
