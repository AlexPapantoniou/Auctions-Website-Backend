package gr.uoa.tedi.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import gr.uoa.tedi.backend.service.AuctionService;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    private AuctionService auctionService;

    public SchedulerConfig(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    // Every 10 seconds scan and activate/deactivate auctions that started/ended
    @Scheduled(fixedRate = 10000)
    public void autoActivateAuctions() {
        auctionService.activateAuctions();
    }

    @Scheduled(fixedRate = 10000)
    public void autoCloseAuctions() {
        auctionService.closeExpiredAuctions();
    }

}
