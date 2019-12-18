package pl.dmcs.rkotas.service;

import java.util.List;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import pl.dmcs.rkotas.domain.AppUser;
import pl.dmcs.rkotas.dto.RegisterForm;

public interface AppUserService {

	void addAppUser(AppUser user);

	void editAppUser(AppUser user);

	List<AppUser> listAppUser();

	void removeAppUser (long id);

	AppUser getAppUser(long id);

	AppUser findByEmail(String login);

	AppUser addUseAfterRegister(RegisterForm registerForm);

	boolean activateUser(long uid);
}

