package pl.dmcs.rkotas.service;

import java.util.List;

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

    @Transactional
    public void addAppUser(AppUser appUser) {
        appUser.getAppUserRole().add(appUserRoleRepository.findByRole("ROLE_USER"));
        appUser.setPassword(hashPassword(appUser.getPassword()));
        appUserRepository.save(appUser);
    }

    @Transactional
    public void editAppUser(AppUser appUser) {
        appUser.getAppUserRole().add(appUserRoleRepository.findByRole("ROLE_USER"));
        appUser.setPassword(hashPassword(appUser.getPassword()));
        appUserRepository.save(appUser);
    }

    private String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    @Transactional
    public List<AppUser> listAppUser() {
        return appUserRepository.findAll();
    }

    @Transactional
    public void removeAppUser(long id) {
        appUserRepository.delete(id);
    }

    @Transactional
    public AppUser getAppUser(long id) {
        return appUserRepository.findById(id);
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
        boolean isExistUser = appUserRepository.existsByUidUser(uid);
        if (!isExistUser) {
            return false;
        }
        AppUser appUser = appUserRepository.findByUidUser(uid);
        appUser.setEnable(true);
        appUserRepository.save(appUser);
        return true;
    }

}


