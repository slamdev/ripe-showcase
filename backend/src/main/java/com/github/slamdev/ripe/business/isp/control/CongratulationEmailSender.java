package com.github.slamdev.ripe.business.isp.control;

import com.github.slamdev.ripe.business.isp.entity.InternetServiceProvider;
import com.github.slamdev.ripe.business.isp.entity.InternetServiceProviderCreationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

@Component
public class CongratulationEmailSender {

    @Value("${mail.congratulations.subject}")
    String subject;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;

    @EventListener
    @Async
    public void sendCongratulationEmail(InternetServiceProviderCreationEvent event) {
        InternetServiceProvider isp = event.getInternetServiceProvider();
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(isp.getEmail());
        email.setSubject(subject);
        email.setText(buildHtmlContent(isp));
        mailSender.send(email);
    }

    String buildHtmlContent(InternetServiceProvider isp) {
        Context context = new Context();
        context.setVariable("name", isp.getCompanyName());
        return templateEngine.process("congratulation", context);
    }
}
