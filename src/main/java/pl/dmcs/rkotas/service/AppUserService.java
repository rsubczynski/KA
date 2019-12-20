package pl.dmcs.rkotas.service;

import pl.dmcs.rkotas.domain.AppUser;
import pl.dmcs.rkotas.domain.Flat;
import pl.dmcs.rkotas.dto.AccommodationForm;
import pl.dmcs.rkotas.dto.EditUserForm;
import pl.dmcs.rkotas.dto.RegisterForm;

public interface AppUserService {

	AppUser findByEmail(String login);

	AppUser addUseAfterRegister(RegisterForm registerForm, Flat flat);

	boolean activateUser(long uid);

	boolean isExistByEmail(String email);

	AppUser addDataToUser(String username, AccommodationForm accommodationForm);

	AppUser editUserData(String username, EditUserForm accommodationForm);

}

