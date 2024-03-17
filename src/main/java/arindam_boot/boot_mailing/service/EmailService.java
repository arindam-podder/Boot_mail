package arindam_boot.boot_mailing.service;

import arindam_boot.boot_mailing.dto.EmailDetails;

public interface EmailService {
    public abstract String sendTextMail(EmailDetails emailDetails);

    public abstract String sendMailWithAttachment(EmailDetails emailDetails);

}
