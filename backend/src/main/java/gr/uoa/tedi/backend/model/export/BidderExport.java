package gr.uoa.tedi.backend.model.export;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class BidderExport {

    @XmlAttribute(name = "Rating")
    private int rating;

    @XmlAttribute(name = "UserID")
    private String userID;

    @XmlElement(name = "Location")
    private String location;

    @XmlElement(name = "Country")
    private String country;

    public BidderExport() {
    }

    public BidderExport(int rating, String userID, String location, String country) {
        this.rating = rating;
        this.userID = userID;
        this.location = location;
        this.country = country;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

}
