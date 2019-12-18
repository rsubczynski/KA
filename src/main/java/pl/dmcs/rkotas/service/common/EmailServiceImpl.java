package pl.dmcs.rkotas.service.common;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.dmcs.rkotas.domain.AppUser;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Service
public class EmailServiceImpl implements EmailService {

    public final JavaMailSender emailSender;

    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendMessageToUser(AppUser appUser, HttpServletRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(appUser.getEmail());
        message.setSubject(getSubject());
        message.setText(getMessage(appUser, createUrl(request), appUser.getSecretFlatCode()));
        emailSender.send(message);
    }

    private String getSubject() {
        return "Witaj w serwisie administracyjnym";
    }

    private String getMessage(AppUser user, String contextPath, String secretCode) {
        return "Aby się zalogować kliknij w link aktywyujacy \n" +
                contextPath + "/active?userUid=" + user.getUidUser() + "\n"
                + "Twoj Secret Code to : tesstowy !:)";
    }

    private String createUrl(HttpServletRequest request) {
        return request.getScheme() + "://" +   // "http" + "://
                request.getServerName() +       // "myhost"
                ":" +                           // ":"
                request.getServerPort();      // "8080"
    }
}
