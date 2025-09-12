package gr.uoa.tedi.backend.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.uoa.tedi.backend.model.Item;
import gr.uoa.tedi.backend.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/auctions")
@CrossOrigin(origins = "https://localhost:4200")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items/owner/{ownerid}")
    public Page<Item> getItemsByOwner(
            @PathVariable Long ownerid,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return itemService.getItemsByOwner(ownerid, page, size);
    }

}
