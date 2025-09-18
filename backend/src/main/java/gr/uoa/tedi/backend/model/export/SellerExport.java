package gr.uoa.tedi.backend.model.export;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class SellerExport {

    @XmlAttribute(name = "Rating")
    private int rating;

    @XmlAttribute(name = "UserID")
    private String userID;

    public SellerExport() {
    }

    public SellerExport(int rating, String userID) {
        this.rating = rating;
        this.userID = userID;
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

}
