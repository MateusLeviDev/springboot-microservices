package com.levi.microservices.notification.service;

import com.levi.microservices.order.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final JavaMailSender javaMailSender;

    @KafkaListener(topics = "order-placed")
    public void listen(OrderPlacedEvent orderPlacedEvent) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("spring-test@email.com");
            messageHelper.setTo(orderPlacedEvent.getEmail());
            messageHelper.setSubject(String.format("Your order %s is placed successfully", orderPlacedEvent.getOrderNumber()));
            messageHelper.setText(String.format("""
                            Hi
                                                
                            Your order %s is now placed successfully. 
                                                
                            Best Regards
                            Levi Help
                            """,
                    orderPlacedEvent.getOrderNumber()));
        };
        try {
            javaMailSender.send(messagePreparator);
            log.info("Order notification email sent.");
        } catch (MailException e) {
            log.error("Exception Occurred When Sending Mail", e);
            throw new RuntimeException("Exception Occurred When Sending Mail to spring-test@mail.com", e);
        }
    }
}
