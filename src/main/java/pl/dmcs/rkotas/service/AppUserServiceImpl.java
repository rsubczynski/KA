package pl.dmcs.rkotas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.rkotas.dao.AppUserRepository;
import pl.dmcs.rkotas.dao.AppUserRoleRepository;
import pl.dmcs.rkotas.domain.AppUser;
import pl.dmcs.rkotas.domain.AppUserData;
import pl.dmcs.rkotas.domain.Flat;
import pl.dmcs.rkotas.dto.AccommodationForm;
import pl.dmcs.rkotas.dto.RegisterForm;

@Service
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepository appUserRepository;
    private AppUserRoleRepository appUserRoleRepository;
    private FlatService flatService;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository, AppUserRoleRepository appUserRoleRepository,
                              FlatService flatService) {
        this.appUserRepository = appUserRepository;
        this.appUserRoleRepository = appUserRoleRepository;
        this.flatService = flatService;
    }

    private String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    @Transactional
    public AppUser findByEmail(String login) {
        return appUserRepository.findByEmail(login);
    }

    @Transactional
    @Override
    public AppUser addUseAfterRegister(RegisterForm registerForm, Flat flat) {
        AppUser appUser = new AppUser();
        appUser.setEmail(registerForm.getEmail());
        appUser.setPassword(hashPassword(registerForm.getPassword()));
        appUser.setUidUser(System.currentTimeMillis());
        appUser.setUserData(new AppUserData());
        appUser.getAppUserRole().add(appUserRoleRepository.findByRole("ROLE_GUEST"));
        appUser.setEnable(false);
        appUser.setSecretFlatCode(flat.getSecretCode());

        return appUserRepository.save(appUser);
    }

    @Override
    public boolean activateUser(long uid) {
        boolean isExistUserByUid = appUserRepository.existsByUidUser(uid);
        if (!isExistUserByUid) {
            return false;
        }
        AppUser appUser = appUserRepository.findByUidUser(uid);
        appUser.setEnable(true);
        appUserRepository.save(appUser);
        return true;
    }

    @Override
    public boolean isExistByEmail(String email) {
        return appUserRepository.existsByEmail(email);
    }

    @Override
    public AppUser addDataToUser(String username, AccommodationForm accommodationForm) {
        AppUser appUser = appUserRepository.findByEmail(username);

        //TODO : works ?
        appUser.getAppUserRole().removeIf(role -> role.getRole().equals("ROLE_GUEST"));

        appUser.getAppUserRole().add(appUserRoleRepository.findByRole("ROLE_USER"));

        AppUserData userData = new AppUserData();
        userData.setFirstName(accommodationForm.getFirstName());
        userData.setLastName(accommodationForm.getLastName());
        userData.setPhoneNumber(accommodationForm.getTelephone());
        userData.setFlat(flatService.findBySecretCode(accommodationForm.getSecretCode()));
        appUser.setUserData(userData);

        return appUserRepository.save(appUser);
    }

}


