package gr.uoa.tedi.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import gr.uoa.tedi.backend.model.Item;
import gr.uoa.tedi.backend.repository.ItemRepository;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Page<Item> getItemsByOwner(Long ownerid, int page, int size) {
        return itemRepository.findByOwnerId(ownerid, PageRequest.of(page, size));
    }
}
