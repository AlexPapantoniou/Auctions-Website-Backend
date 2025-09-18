package gr.uoa.tedi.backend.model.export;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ItemExport {

    @XmlAttribute(name = "ItemID")
    private Long itemID;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Category")
    private List<String> categories = new ArrayList<>();

    @XmlElement(name = "Currently")
    private String currently;

    @XmlElement(name = "Buy_Price")
    private String buyPrice;

    @XmlElement(name = "First_Bid")
    private String firstBid;

    @XmlElement(name = "Number_of_Bids")
    private int numberOfBids;

    @XmlElement(name = "Bids")
    private BidsExport bids;

    @XmlElement(name = "Location")
    private String location;

    @XmlElement(name = "Country")
    private String country;

    @XmlElement(name = "Started")
    private Instant started;

    @XmlElement(name = "Ends")
    private Instant ends;

    @XmlElement(name = "Seller")
    private SellerExport seller;

    @XmlElement(name = "Description")
    private String description;

    public ItemExport() {
    }

    public ItemExport(Long itemID, String name, List<String> categories, String currently, String buyPrice,
            String firstBid, int numberOfBids, BidsExport bids, String location, String country, Instant started,
            Instant ends, SellerExport seller, String description) {
        this.itemID = itemID;
        this.name = name;
        this.categories = categories;
        this.currently = currently;
        this.buyPrice = buyPrice;
        this.firstBid = firstBid;
        this.numberOfBids = numberOfBids;
        this.bids = bids;
        this.location = location;
        this.country = country;
        this.started = started;
        this.ends = ends;
        this.seller = seller;
        this.description = description;
    }

    public Long getItemID() {
        return itemID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getCurrently() {
        return currently;
    }

    public void setCurrently(String currently) {
        this.currently = currently;
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getFirstBid() {
        return firstBid;
    }

    public void setFirstBid(String firstBid) {
        this.firstBid = firstBid;
    }

    public int getNumberOfBids() {
        return numberOfBids;
    }

    public void setNumberOfBids(int numberOfBids) {
        this.numberOfBids = numberOfBids;
    }

    public BidsExport getBids() {
        return bids;
    }

    public void setBids(BidsExport bids) {
        this.bids = bids;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Instant getStarted() {
        return started;
    }

    public void setStarted(Instant started) {
        this.started = started;
    }

    public Instant getEnds() {
        return ends;
    }

    public void setEnds(Instant ends) {
        this.ends = ends;
    }

    public SellerExport getSeller() {
        return seller;
    }

    public void setSeller(SellerExport seller) {
        this.seller = seller;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
