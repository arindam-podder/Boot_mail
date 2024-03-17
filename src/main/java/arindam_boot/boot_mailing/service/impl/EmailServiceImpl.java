package arindam_boot.boot_mailing.service.impl;

import arindam_boot.boot_mailing.dto.EmailDetails;
import arindam_boot.boot_mailing.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendTextMail(EmailDetails emailDetails) {
        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setFrom("Boot_mail");
            simpleMailMessage.setTo(emailDetails.getRecipient());
            simpleMailMessage.setSubject(emailDetails.getSubject());
            simpleMailMessage.setText(emailDetails.getMsgBody());

            //sending the mail
            javaMailSender.send(simpleMailMessage);
            return "mail sent successfully...";
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return "exception occur during mail send.";
        }
    }

    @Override
    public String sendMailWithAttachment(EmailDetails emailDetails) {
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

//            mimeMessageHelper.setFrom("boot_mail");
            mimeMessageHelper.setTo(emailDetails.getRecipient());
            mimeMessageHelper.setSubject(emailDetails.getSubject());
            mimeMessageHelper.setText(emailDetails.getMsgBody());

            //adding the attachment
            FileSystemResource resource = new FileSystemResource(new File(emailDetails.getAttachment()));
            mimeMessageHelper.addAttachment(resource.getFilename(), resource);

            //send the mail
            javaMailSender.send(mimeMessage);
            return "mail sent successfully.";
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return "exception occur while sending mail with attachment.";
        }
    }
}
