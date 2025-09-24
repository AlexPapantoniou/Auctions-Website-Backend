package gr.uoa.tedi.backend.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageid")
    private Long messageid;

    @ManyToOne
    @JoinColumn(name = "senderid", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "auctionid", nullable = false)
    private Auction auction;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "timestamp", columnDefinition = "TIMESTAMP(6)", nullable = false)
    private Instant timestamp = Instant.now();

    public Message() {
    }

    public Message(User sender, User receiver, Auction auction, String content) {
        this.sender = sender;
        this.auction = auction;
        this.content = content;
    }

    public Long getMessageid() {
        return messageid;
    }

    public void setMessageid(Long messageid) {
        this.messageid = messageid;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

}
