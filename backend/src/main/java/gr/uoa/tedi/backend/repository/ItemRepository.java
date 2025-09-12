package gr.uoa.tedi.backend.repository;

import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import gr.uoa.tedi.backend.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT DISTINCT a.item from Auction a WHERE a.seller.userid = :ownerid AND a.active = false")
    Page<Item> findByOwnerId(Long ownerid, Pageable pageable);
}
