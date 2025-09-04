package gr.uoa.tedi.backend.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemid")
    private long itemid;

    @OneToOne(mappedBy = "item")
    @JsonIgnore
    private Auction auction;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "itemcategory", joinColumns = @JoinColumn(name = "itemid"), inverseJoinColumns = @JoinColumn(name = "categoryid"))
    private List<Category> categories = new ArrayList<>();

    @Column(name = "location")
    private String location;

    @Column(name = "country")
    private String country;

    @Column(name = "description", length = 1000)
    private String description;

    protected Item() {
    }

    public Item(String name, List<Category> categories, String location, String country,
            String description) {
        this.name = name;
        this.categories = categories;
        this.location = location;
        this.country = country;
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}