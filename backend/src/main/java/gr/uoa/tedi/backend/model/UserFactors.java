package gr.uoa.tedi.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_factors")
public class UserFactors {

    @Id
    private Long userid;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String factors;

    public UserFactors() {
    }

    public UserFactors(User user, String factors) {
        this.user = user;
        this.factors = factors;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFactors() {
        return factors;
    }

    public void setFactors(String factors) {
        this.factors = factors;
    }

}
