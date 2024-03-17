package arindam_boot.boot_mailing.controller;

import arindam_boot.boot_mailing.dto.EmailDetails;
import arindam_boot.boot_mailing.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendMail")
    public ResponseEntity<String> sendMail(@RequestBody EmailDetails emailDetails){
        String textMail = emailService.sendTextMail(emailDetails);

        return new ResponseEntity<>(textMail, HttpStatus.OK);
    }

    @PostMapping("/sendMailWithAttachment")
    public ResponseEntity<String> sendMailWithAttachment(@RequestBody EmailDetails emailDetails){
        String response = emailService.sendMailWithAttachment(emailDetails);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
