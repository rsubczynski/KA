package pl.dmcs.rkotas.service;

import pl.dmcs.rkotas.domain.AppUser;
import pl.dmcs.rkotas.dto.RegisterForm;

public interface AppUserService {

	AppUser findByEmail(String login);

	AppUser addUseAfterRegister(RegisterForm registerForm);

	boolean activateUser(long uid);

	boolean isExistByEmail(String email);

}

