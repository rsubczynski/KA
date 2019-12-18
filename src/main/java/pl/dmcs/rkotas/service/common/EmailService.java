package pl.dmcs.rkotas.service.common;

import pl.dmcs.rkotas.domain.AppUser;

import javax.servlet.http.HttpServletRequest;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);

    void sendSimpleMessage(AppUser registerForm, HttpServletRequest request);
}
