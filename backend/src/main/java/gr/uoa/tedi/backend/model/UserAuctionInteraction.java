package gr.uoa.tedi.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_auction_interactions", uniqueConstraints = @UniqueConstraint(columnNames = { "userid", "auctionid",
        "interaction_type" }))
public class UserAuctionInteraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interactionid")
    private Long interactionid;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "auctionid", nullable = false)
    private Auction auction;

    @Column(name = "interaction_type")
    private String interactionType;

    @Column(name = "weight")
    private Double weight;

    public UserAuctionInteraction() {
    }

    public UserAuctionInteraction(User user, Auction auction, String interactionType, Double weight) {
        this.user = user;
        this.auction = auction;
        this.interactionType = interactionType;
        this.weight = weight;
    }

    public Long getInteractionid() {
        return interactionid;
    }

    public void setInteractionid(Long interactionid) {
        this.interactionid = interactionid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public String getInteractionType() {
        return interactionType;
    }

    public void setInteractionType(String interactionType) {
        this.interactionType = interactionType;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

}
