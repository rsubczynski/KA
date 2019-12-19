package pl.dmcs.rkotas.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public interface IAuthenticationFacade {
    Authentication getAuthentication();

    UserDetails getLoginUser();
}
