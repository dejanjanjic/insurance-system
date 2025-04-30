package net.etfbl.sni.insurancesystem.service;

import net.etfbl.sni.insurancesystem.exception.EmailSendingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendActivationEmail(String toEmail, String activationCode) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(toEmail);
            helper.setSubject("Aktivacioni kod za vaš nalog");
            helper.setText("Poštovani,\n\n"
                    + "Vaš aktivacioni kod je: " + activationCode
                    + "\n\nMolimo koristite ovaj kod da aktivirate vaš nalog."
                    + "\n\nHvala,\nInsurance System");
            mailSender.send(message);
            System.out.println("Aktivacioni email poslat na: " + toEmail);
        } catch (MessagingException | MailException e) {
            System.err.println("Greška prilikom slanja aktivacionog emaila na " + toEmail + ": " + e.getMessage());
            throw new EmailSendingException("Could not send activation email");
        }
    }
}