package pl.dmcs.rkotas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.rkotas.dao.AppUserRepository;
import pl.dmcs.rkotas.dao.AppUserRoleRepository;
import pl.dmcs.rkotas.domain.AppUser;
import pl.dmcs.rkotas.dto.RegisterForm;

@Service
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepository appUserRepository;
    private AppUserRoleRepository appUserRoleRepository;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository, AppUserRoleRepository appUserRoleRepository) {
        this.appUserRepository = appUserRepository;
        this.appUserRoleRepository = appUserRoleRepository;
    }


    private String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    @Transactional
    public AppUser findByEmail(String login) {
        return appUserRepository.findByEmail(login);
    }

    @Override
    public AppUser addUseAfterRegister(RegisterForm registerForm) {
        AppUser appUser = new AppUser();
        appUser.setEmail(registerForm.getEmail());
        appUser.setPassword(hashPassword(registerForm.getPassword()));
        appUser.setUidUser(System.currentTimeMillis());
        appUser.getAppUserRole().add(appUserRoleRepository.findByRole("ROLE_GUEST"));
        appUser.setEnable(false);
        appUser.setSecretFlatCode("secretCode");

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

}


