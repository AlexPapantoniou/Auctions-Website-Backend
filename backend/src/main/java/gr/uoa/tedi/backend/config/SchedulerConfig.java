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

    @Scheduled(fixedRate = 60000)
    public void autoCloseAuctions() {
        auctionService.closeExpiredAuctions();
    }

    @Scheduled(fixedRate = 60000)
    public void autoActivateAuctions() {
        auctionService.activateAuctions();
    }
}
