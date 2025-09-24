package gr.uoa.tedi.backend.model.export;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Auctions")
@XmlAccessorType(XmlAccessType.FIELD)
public class AuctionsExport {

    @XmlElement(name = "Item")
    private List<AuctionExport> auctions = new ArrayList<>();

    public AuctionsExport() {
    }

    public AuctionsExport(List<AuctionExport> auctions) {
        this.auctions = auctions;
    }

    public List<AuctionExport> getauctions() {
        return auctions;
    }

    public void setauctions(List<AuctionExport> auctions) {
        this.auctions = auctions;
    }
}
