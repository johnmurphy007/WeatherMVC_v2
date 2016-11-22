package ie.cit.group3.utility;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ie.cit.group3.entity.Weather;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Component
public class SendEMail 
{
/*    private JavaMailSender mailSender;
    
    public SendEMail() {
		super();
	//	this.setMailSender(mailSender.createMimeMessage());
		
		// TODO Auto-generated constructor stub
	}

	public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void SendEmail(final Weather weather) {

        // Do the business calculations...
 
					        // Call the collaborators to persist the order...
					    final	InternetAddress emailTo = new InternetAddress();
					     emailTo.setAddress("johnmurphy.ber@gmail.com");
					     
					     final InternetAddress emailFrom = new InternetAddress();
					     emailFrom.setAddress("johnmurphy007@gmail.com");
					    	
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
        	//anonymous inner class:
        		public void prepare(MimeMessage mimeMessage) throws Exception {
  
            	mimeMessage.setRecipient(Message.RecipientType.TO, emailTo);
                mimeMessage.setFrom(emailFrom);
                mimeMessage.setText("Dear John, the weather is" +weather.getTemp_c());
            }
        };

        try {
            mailSender.send(preparator);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }*/
    
	private MailSender mailSender;
	private SimpleMailMessage templateMessage;

	public void setMailSender(MailSender mailSender) 
	{
		this.mailSender = mailSender;
	}

	public void setTemplateMessage()//SimpleMailMessage templateMessage) 
	{
		//this.templateMessage = templateMessage;
		this.templateMessage.setFrom("johnmurphy.ber@gmail.com");
		this.templateMessage.setTo("johnmurphy007@gmail.com");
		this.templateMessage.setText("test");
	}
	// Do the business calculations...

	// Call the collaborators to persist the order...

	// Create a thread safe "copy" of the template message and customize it
	public void SendEmail(Weather weather) {
		
        SimpleMailMessage msg = new SimpleMailMessage();//this.templateMessage);
        msg.setTo("johnmurphy007@gmail.com");
        msg.setFrom("johnmurphy.ber@gmail.com");
        msg.setText("Test e-mail for John...can add in contents of recent downloads if this works: "+weather);
        try{
            this.mailSender.send(msg);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }

}
