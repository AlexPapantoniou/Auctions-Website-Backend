package gr.uoa.tedi.backend.model.export;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class BidsExport {

    @XmlElement(name = "Bids")
    private List<BidExport> bids = new ArrayList<>();

    public BidsExport() {
    }

    public BidsExport(List<BidExport> bids) {
        this.bids = bids;
    }

    public List<BidExport> getBids() {
        return bids;
    }

    public void setBids(List<BidExport> bids) {
        this.bids = bids;
    }

}
