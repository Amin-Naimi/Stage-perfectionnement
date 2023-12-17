package stage.stage.utiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailUtils {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String toEmail,
                                  String body,
                                  String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ahnbi32@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        emailSender.send(message);

        System.out.println("Mail send successfuly");

    }


}
