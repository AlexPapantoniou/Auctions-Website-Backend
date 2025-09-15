package gr.uoa.tedi.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auctions")
@CrossOrigin(origins = "https://localhost:4200")
public class MessageController {

}
