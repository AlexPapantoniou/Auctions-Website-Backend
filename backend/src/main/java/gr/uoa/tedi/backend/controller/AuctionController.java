package gr.uoa.tedi.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import gr.uoa.tedi.backend.model.Auction;
import gr.uoa.tedi.backend.service.AuctionService;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/auctions")
@CrossOrigin(origins = "https://localhost:4200")
public class AuctionController {

    private final AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping("/search/{userid}")
    public Page<Auction> searchAuctions(
            @PathVariable Long userid,
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return auctionService.searchAuctions(userid, keyword, page, size);
    }

    @GetMapping("/seller/{sellerid}/auctions")
    public Page<Auction> getAuctionsBySeller(
            @PathVariable Long sellerid,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return auctionService.getAuctionsBySeller(sellerid, page, size);
    }

    @GetMapping("/{auctionid}")
    public ResponseEntity<Auction> getAuctionById(@PathVariable Long auctionid) {
        Auction auction = auctionService.getAuctionById(auctionid);
        return auction != null ? ResponseEntity.ok(auction) : ResponseEntity.notFound().build();
    }

    @GetMapping("/locations")
    public List<String> getAllLocations() {
        return auctionService.getAllLocations();
    }

    @GetMapping("/cities")
    public List<String> getAllCities() {
        return auctionService.getAllCities();
    }

    @GetMapping("/countries")
    public List<String> getAllCountries() {
        return auctionService.getAllCountries();
    }

    @GetMapping("/minPrice")
    public Double getMinPrice() {
        return auctionService.getMinPrice();
    }

    @GetMapping("/maxPrice")
    public Double getMaxPrice() {
        return auctionService.getMaxPrice();
    }

    @GetMapping("/filtered/{userid}/{category}/{location}/{city}/{country}/{minPrice}/{maxPrice}")
    public Page<Auction> getAuctionsFilteredOrderedByWeight(
            @PathVariable Long userid,
            @PathVariable String category,
            @PathVariable String location,
            @PathVariable String city,
            @PathVariable String country,
            @PathVariable Double minPrice,
            @PathVariable Double maxPrice,
            @RequestParam(defaultValue = "false") boolean activeOnly,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return auctionService.getAuctionsFilteredOrderedByWeight(userid, category, location, city, country, minPrice,
                maxPrice, activeOnly, page, size);
    }

    @PostMapping("/addauction")
    public ResponseEntity<Auction> addAuction(@RequestBody Auction auction) {
        Auction saved = auctionService.registerAuction(auction);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/{auctionid}/buy/{bidderid}")
    public ResponseEntity<Auction> buyNow(
            @PathVariable Long auctionid,
            @PathVariable Long bidderid) {
        try {
            Auction auction = auctionService.buyNow(auctionid, bidderid);
            return ResponseEntity.ok(auction);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/{auctionid}")
    public ResponseEntity<Auction> updateAuction(
            @PathVariable Long auctionid,
            @RequestBody Auction updatedAuction) {
        return auctionService.updateAuction(auctionid, updatedAuction)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{auctionid}")
    public ResponseEntity<Void> deleteAuction(@PathVariable Long auctionid) {
        auctionService.deleteAuction(auctionid);
        return ResponseEntity.noContent().build();
    }

}
