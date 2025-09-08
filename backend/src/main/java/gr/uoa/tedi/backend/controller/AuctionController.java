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
@CrossOrigin(origins = "http://localhost:4200")
public class AuctionController {

    private final AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping
    public Page<Auction> getAllAuctions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return auctionService.getAllAuctions(page, size);
    }

    @GetMapping("/search")
    public Page<Auction> searchAuctions(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return auctionService.searchAuctions(keyword, page, size);
    }

    @GetMapping("/category")
    public Page<Auction> searchAuctionsByCategory(
            @RequestParam String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return auctionService.searchAuctionsByCategory(category, page, size);
    }

    @GetMapping("seller/{sellerid}/auctions")
    public Page<Auction> getAuctionsBySeller(
            @PathVariable Long sellerid,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return auctionService.getAuctionsBySeller(sellerid, page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auction> getAuctionById(@PathVariable Long id) {
        Auction auction = auctionService.getAuctionById(id);
        return auction != null ? ResponseEntity.ok(auction) : ResponseEntity.notFound().build();
    }

    @GetMapping("/locations")
    public List<String> getAllLocations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return auctionService.getAllLocations();
    }

    @GetMapping("/cities")
    public List<String> getAllCities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return auctionService.getAllCities();
    }

    @GetMapping("/countries")
    public List<String> getAllCountries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return auctionService.getAllCountries();
    }

    @GetMapping("items/location/{location}")
    public Page<Auction> getAuctionsByLocation(
            @PathVariable String location,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return auctionService.getAuctionsByLocation(location, page, size);
    }

    @GetMapping("items/city/{city}")
    public Page<Auction> getAuctionsByCity(
            @PathVariable String city,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return auctionService.getAuctionsByCity(city, page, size);
    }

    @GetMapping("items/country/{country}")
    public Page<Auction> getAuctionsByCountry(
            @PathVariable String country,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return auctionService.getAuctionsByCountry(country, page, size);
    }

    @PostMapping("/addauction")
    public Auction addAuction(@RequestBody Auction auction) {
        return auctionService.registerAuction(auction);
    }

    @PutMapping("/update/{auctionid}")
    public ResponseEntity<Auction> updateAuction(
            @PathVariable Long auctionid,
            @RequestBody Auction updatedAuction) {
        return auctionService.updateAuction(auctionid, updatedAuction)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAuction(@PathVariable Long auctionid) {
        auctionService.deleteAuction(auctionid);
        return ResponseEntity.noContent().build();
    }

}
