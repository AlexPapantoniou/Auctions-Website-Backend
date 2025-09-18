package gr.uoa.tedi.backend.model.export;

import java.time.Instant;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class BidExport {

    @XmlElement(name = "Bidder")
    private BidderExport bidder;

    @XmlElement(name = "Time")
    private Instant time;

    @XmlElement(name = "Amount")
    private String amount;

    public BidExport() {
    }

    public BidExport(BidderExport bidder, Instant time, String amount) {
        this.bidder = bidder;
        this.time = time;
        this.amount = amount;
    }

    public BidderExport getBidder() {
        return bidder;
    }

    public void setBidder(BidderExport bidder) {
        this.bidder = bidder;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
