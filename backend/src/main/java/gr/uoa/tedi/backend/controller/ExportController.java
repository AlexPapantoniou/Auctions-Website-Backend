package gr.uoa.tedi.backend.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpHeaders;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.uoa.tedi.backend.model.Auction;
import gr.uoa.tedi.backend.model.export.AuctionExport;
import gr.uoa.tedi.backend.model.export.AuctionsExport;
import gr.uoa.tedi.backend.repository.AuctionRepository;
import gr.uoa.tedi.backend.service.ExportService;

@RestController
@RequestMapping("/auctions")
@CrossOrigin(origins = "https://localhost:4200")
public class ExportController {

    private final ExportService exportService;
    private final AuctionRepository auctionRepository;

    public ExportController(
            ExportService exportService,
            AuctionRepository auctionRepository) {
        this.exportService = exportService;
        this.auctionRepository = auctionRepository;
    }

    @GetMapping(value = "/export/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public AuctionsExport exportAuctionsAsXml() {
        return exportService.exportAuctionsAsXml();
    }

    @GetMapping(value = "/export/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> exportAuctionsAsJSON() throws IOException {
        List<Auction> auctions = auctionRepository.findAll();

        List<AuctionExport> mappedAuctions = auctions.stream()
                .map(auction -> exportService.mapAuctionToItemExport(auction))
                .collect(Collectors.toList());

        String json = exportService.exportAuctionsAsJSON(mappedAuctions);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=auctions.json")
                .contentType(MediaType.APPLICATION_JSON)
                .body(json);
    }

}
