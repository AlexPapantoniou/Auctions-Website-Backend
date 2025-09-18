package gr.uoa.tedi.backend.controller;

import gr.uoa.tedi.backend.model.User;
import gr.uoa.tedi.backend.model.export.ItemsExport;
import gr.uoa.tedi.backend.service.AuctionService;
import gr.uoa.tedi.backend.service.UserService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/auctions")
@CrossOrigin(origins = "https://localhost:4200")
public class AdminController {

    private final UserService userService;
    private final AuctionService auctionSerice;

    public AdminController(
            UserService userService,
            AuctionService auctionService) {
        this.userService = userService;
        this.auctionSerice = auctionService;
    }

    @GetMapping("/admin/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllExcludingAdmin());
    }

    @PutMapping("/admin/users/{userid}/accept")
    public ResponseEntity<User> acceptUser(@PathVariable Long userid) {
        return ResponseEntity.ok(userService.acceptUser(userid));
    }

    @DeleteMapping("/admin/users/{userid}/delete")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userid) {
        userService.deleteUser(userid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "admin/export/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ItemsExport exportAuctionsAsXml() {
        return auctionSerice.exportAuctionsAsXml();
    }

}
