package gr.uoa.tedi.backend.model;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemid")
    private long itemid;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "item_category", joinColumns = @JoinColumn(name = "item_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Collection<Category> categories;

    @Column(name = "currentbid")
    private double currentbid;

    @Column(name = "buyprice")
    private double buyprice;

    @Column(name = "firstbid")
    private double firstbid;

    @Column(name = "numberofbids")
    private int numberofbids;

    @OneToMany(mappedBy = "item")
    private Collection<Bid> bids;

    @Column(name = "location")
    private String location;

    @Column(name = "country")
    private String country;

    @Column(name = "started")
    private String started;

    @Column(name = "ends")
    private String ends;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @Column(name = "description", length = 1000)
    private String description;

    protected Item() {
    }

    public Item(String name, Collection<Category> categories, double currentbid, double buyprice, double firstbid,
            int numberofbids, Collection<Bid> bids, String location, String country, String started, String ends,
            User seller, String description) {
        this.name = name;
        this.categories = categories;
        this.currentbid = currentbid;
        this.buyprice = buyprice;
        this.firstbid = firstbid;
        this.numberofbids = numberofbids;
        this.bids = bids;
        this.location = location;
        this.country = country;
        this.started = started;
        this.ends = ends;
        this.seller = seller;
        this.description = description;
    }

    public long getItemid() {
        return itemid;
    }

    public void setItemid(long itemid) {
        this.itemid = itemid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Category> getCategories() {
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }

    public double getCurrentbid() {
        return currentbid;
    }

    public void setCurrentbid(double currentbid) {
        this.currentbid = currentbid;
    }

    public double getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(double buyprice) {
        this.buyprice = buyprice;
    }

    public double getFirstbid() {
        return firstbid;
    }

    public void setFirstbid(double firstbid) {
        this.firstbid = firstbid;
    }

    public int getNumberofbids() {
        return numberofbids;
    }

    public void setNumberofbids(int numberofbids) {
        this.numberofbids = numberofbids;
    }

    public Collection<Bid> getBids() {
        return bids;
    }

    public void setBids(Collection<Bid> bids) {
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

    public String getStarted() {
        return started;
    }

    public void setStarted(String started) {
        this.started = started;
    }

    public String getEnds() {
        return ends;
    }

    public void setEnds(String ends) {
        this.ends = ends;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}