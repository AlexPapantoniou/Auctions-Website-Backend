package gr.uoa.tedi.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long userid;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "email")
    private String email;

    @Column(name = "phonenumber")
    private String phonenumber;

    @Column(name = "afm")
    private String afm;

    @Column(name = "accepted", nullable = false)
    private boolean accepted = false;

    @Column(name = "bidder-rating")
    private Integer bidderRating = 0;

    @Column(name = "seller-rating")
    private Integer sellerRating = 0;

    public User() {
    }

    public User(String user_name, String pass_word, String first_name, String last_name, String city,
            String country, String email, String phone_number, String AFM) {
        this.username = user_name;
        this.password = pass_word;
        this.firstname = first_name;
        this.lastname = last_name;
        this.city = city;
        this.country = country;
        this.email = email;
        this.phonenumber = phone_number;
        this.afm = AFM;
        this.accepted = false;
        this.bidderRating = 0;
        this.sellerRating = 0;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getcountry() {
        return country;
    }

    public void setcountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAFM() {
        return afm;
    }

    public void setAFM(String AFM) {
        this.afm = AFM;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Integer getBidderRating() {
        return bidderRating;
    }

    public void setBidderRating(Integer bidderRating) {
        this.bidderRating = bidderRating;
    }

    public Integer getSellerRating() {
        return sellerRating;
    }

    public void setSellerRating(Integer sellerRating) {
        this.sellerRating = sellerRating;
    }

}
