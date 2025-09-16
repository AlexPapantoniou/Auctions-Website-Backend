package gr.uoa.tedi.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import gr.uoa.tedi.backend.model.Auction;
import gr.uoa.tedi.backend.model.Message;
import gr.uoa.tedi.backend.repository.AuctionRepository;
import gr.uoa.tedi.backend.repository.MessageRepository;

@Service
public class MessageService {

    private MessageRepository messageRepository;
    private AuctionRepository auctionRepository;

    public MessageService(
            MessageRepository messageRepository,
            AuctionRepository auctionRepository) {
        this.messageRepository = messageRepository;
        this.auctionRepository = auctionRepository;
    }

    public List<Message> getMessagesByAuction(Long auctionid) {
        Auction auction = this.auctionRepository.findById(auctionid)
                .orElseThrow(() -> new RuntimeException("Auction not found"));

        return messageRepository.findByAuctionOrderByTimestampAsc(auction);
    }

    public Message sendMessage(Message message) {
        return this.messageRepository.save(message);
    }

}
