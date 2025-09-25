package gr.uoa.tedi.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.uoa.tedi.backend.model.Message;
import gr.uoa.tedi.backend.service.MessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auctions/messages")
@CrossOrigin(origins = "https://localhost:4200")
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/{auctionid}")
    public List<Message> getMessagesByAuction(@PathVariable Long auctionid) {
        return messageService.getMessagesByAuction(auctionid);
    }

    @GetMapping("/{auctionid}/unread-count/{receiverid}")
    public Long getUnreadMessagesCount(
            @PathVariable Long auctionid,
            @PathVariable Long receiverid) {
        return messageService.getUnreadMessagesCount(auctionid, receiverid);
    }

    @PostMapping("/read")
    public Message messageWasRead(@RequestBody Message message) {
        return messageService.messageWasRead(message);
    }

    @PostMapping("/send")
    public Message sendMessage(@RequestBody Message message) {
        return messageService.sendMessage(message);
    }

    @DeleteMapping("/delete/{messageid}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long messageid) {
        messageService.deleteMessage(messageid);
        return ResponseEntity.noContent().build();
    }

}
