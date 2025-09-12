package gr.uoa.tedi.backend.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private List<Bid> bids = new ArrayList<>();

    @Column(name = "starttime")
    private LocalDateTime startTime;

    @Column(name = "endtime")
    private LocalDateTime endTime;

    @Column(name = "active")
    private Boolean active;

    public Auction() {
    }

    public Auction(User seller, Item item, Double firstBid, Double buyPrice, LocalDateTime startTime,
            LocalDateTime endTime, String address, String location, String city, String country) {
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
        this.active = true;
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

    public Double getbuyPrice() {
        return buyPrice;
    }

    public void setbuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Integer getNumberOfBids() {
        return numberOfBids;
    }

    public void setNumberOfBids(Integer numberOfBids) {
        this.numberOfBids = numberOfBids;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
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
