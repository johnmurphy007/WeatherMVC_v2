package ie.cit.group3.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


@PropertySource("classpath:mail.properties")
@Component
class SmtpMailSender {

	@Autowired
	private JavaMailSender javaMailSender;
	
    public void send(String to, String subject, String body)
    {
    	MimeMessage message = javaMailSender.createMimeMessage();
    	MimeMessageHelper helper; //used to help create the
    	
    	try {
			helper = new MimeMessageHelper (message, true); //true indicates multipart message

			helper.setSubject(subject);
			helper.setTo(to);
			helper.setText(body, true); //true indicates html
			//continue using helper object for more functionality like adding attachments, etc refer to Spring docs
			
			javaMailSender.send(message);
		} catch (MailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}