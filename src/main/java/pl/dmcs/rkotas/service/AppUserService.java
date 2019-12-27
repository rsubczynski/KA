package pl.dmcs.rkotas.service;

import pl.dmcs.rkotas.domain.AppUser;
import pl.dmcs.rkotas.domain.Flat;
import pl.dmcs.rkotas.dto.AccommodationForm;
import pl.dmcs.rkotas.dto.EditUserForm;
import pl.dmcs.rkotas.dto.MeterForm;
import pl.dmcs.rkotas.dto.RegisterForm;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface AppUserService {

    AppUser findByEmail(String login);

    AppUser addUseAfterRegister(RegisterForm registerForm, Flat flat);

    boolean activateUser(long uid);

    boolean isExistByEmail(String email);

    void addDataToUser(String username, AccommodationForm accommodationForm);

    void editUserData(String username, EditUserForm accommodationForm);

    void addMeterToUser(AppUser appUser, MeterForm meterForm);

    void createTempSuperUserAccount(String login, String password);

    void createTempAdminAccount(String login, String password);

    List<AppUser> findAllUserWithGuestRole();

    List<AppUser> findAllUserWithUserRole();

    List<AppUser> findAllUserWithSuperUserRole();

    List<AppUser> findAllUserWithAdminRole();

    void deleteUser(long userId);

    AppUser findById(long userId);

}

