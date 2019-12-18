package pl.dmcs.rkotas.service.common;

import pl.dmcs.rkotas.domain.AppUser;

import javax.servlet.http.HttpServletRequest;

public interface EmailService {
    void sendMessageToUser(AppUser registerForm, HttpServletRequest request);
}
