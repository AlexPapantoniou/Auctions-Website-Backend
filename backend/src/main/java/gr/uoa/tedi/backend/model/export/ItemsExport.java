package gr.uoa.tedi.backend.model.export;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Items")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemsExport {

    @XmlElement(name = "Item")
    private List<ItemExport> items = new ArrayList<>();

    public ItemsExport() {
    }

    public ItemsExport(List<ItemExport> items) {
        this.items = items;
    }

    public List<ItemExport> getItems() {
        return items;
    }

    public void setItems(List<ItemExport> items) {
        this.items = items;
    }
}
