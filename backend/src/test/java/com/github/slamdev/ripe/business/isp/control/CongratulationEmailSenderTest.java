package com.github.slamdev.ripe.business.isp.control;

import com.github.slamdev.ripe.business.isp.entity.InternetServiceProvider;
import com.github.slamdev.ripe.business.isp.entity.InternetServiceProviderCreationEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CongratulationEmailSenderTest {

    @InjectMocks
    @Spy
    private CongratulationEmailSender sender;

    @Mock
    private JavaMailSender mailSender;

    @Before
    public void setUp() {
        // Spying is used since we cannot mock final methods of SpringTemplateEngine
        doReturn("").when(sender).buildHtmlContent(anyObject());
    }

    @Test
    public void should_send_email() {
        InternetServiceProvider isp = InternetServiceProvider.builder()
                .companyName("Company").email("some@email.com").build();
        sender.subject = "some subject";
        InternetServiceProviderCreationEvent event = new InternetServiceProviderCreationEvent(isp);
        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        sender.sendCongratulationEmail(event);
        verify(mailSender, times(1)).send(captor.capture());
        SimpleMailMessage message = captor.getValue();
        assertThat(message.getSubject(), is("some subject"));
        assertThat(message.getTo(), hasItemInArray("some@email.com"));
    }
}