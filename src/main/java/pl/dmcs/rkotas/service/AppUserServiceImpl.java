package pl.dmcs.rkotas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.rkotas.dao.AppUserRepository;
import pl.dmcs.rkotas.dao.AppUserRoleRepository;
import pl.dmcs.rkotas.domain.*;
import pl.dmcs.rkotas.domain.charges.*;
import pl.dmcs.rkotas.dto.AccommodationForm;
import pl.dmcs.rkotas.dto.EditUserForm;
import pl.dmcs.rkotas.dto.MeterForm;
import pl.dmcs.rkotas.dto.RegisterForm;
import pl.dmcs.rkotas.util.PaymentStatus;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepository appUserRepository;
    private AppUserRoleRepository appUserRoleRepository;
    private FlatService flatService;
    private final BlockService blockService;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository, AppUserRoleRepository appUserRoleRepository,
                              FlatService flatService, BlockService blockService) {
        this.appUserRepository = appUserRepository;
        this.appUserRoleRepository = appUserRoleRepository;
        this.flatService = flatService;
        this.blockService = blockService;
    }

    private String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public AppUser findByEmail(String login) {
        return appUserRepository.findByEmail(login);
    }


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
    public void addDataToUser(String username, AccommodationForm accommodationForm) {
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

        appUserRepository.save(appUser);
    }

    @Override
    public void editUserData(String username, EditUserForm editUserForm) {
        AppUser appUser = appUserRepository.findByEmail(username);
        AppUserData userData = appUser.getUserData();
        userData.setFirstName(editUserForm.getFirstName());
        userData.setLastName(editUserForm.getLastName());
        userData.setPhoneNumber(editUserForm.getTelephone());
        appUserRepository.save(appUser);
    }

    @Override
    public void addMeterToUser(AppUser appUser, MeterForm meterForm) {
        Rates rates = appUser.getUserData().getFlat().getRates();
        ColdWater coldWater = ColdWater.builder()
                .rate(rates.getColdWaterRate())
                .count(Double.parseDouble(meterForm.getColdWater()))
                .price(rates.getColdWaterRate() * Double.parseDouble(meterForm.getColdWater()))
                .build();

        HotWater hotWater = HotWater.builder()
                .rate(rates.getHotWaterRate())
                .count(Double.parseDouble(meterForm.getHotWater()))
                .price(rates.getHotWaterRate() * Double.parseDouble(meterForm.getHotWater()))
                .build();

        Electricity electricity = Electricity.builder()
                .rate(rates.getElectricityRate())
                .count(Double.parseDouble(meterForm.getElectricity()))
                .price(rates.getElectricityRate() * Double.parseDouble(meterForm.getElectricity()))
                .build();

        Heating heating = Heating.builder()
                .rate(rates.getHeatingRate())
                .count(Double.parseDouble(meterForm.getHeating()))
                .price(rates.getHeatingRate() * Double.parseDouble(meterForm.getHeating()))
                .build();

        RepairFund repairFund = RepairFund.builder()
                .rate(rates.getRepairFundRate())
                .count(1)
                .price(rates.getRepairFundRate())
                .build();

        Bill bill = new Bill();
        bill.setLocaleData(LocalDateTime.now());
        bill.setColdWater(coldWater);
        bill.setHotWater(hotWater);
        bill.setElectricityList(electricity);
        bill.setHeating(heating);
        bill.setRepairFund(repairFund);
        bill.setPaymentStatus(PaymentStatus.NEW);

        bill.setPayment(false);
        bill.setTotalCount(coldWater.getPrice() + hotWater.getPrice() +
                electricity.getPrice() + heating.getPrice() + repairFund.getPrice());

        appUser.getUserData().getFlat().getBills().add(bill);

        appUserRepository.save(appUser);
    }

    @Override
    public void createTempSuperUserAccount(String login, String password) {
        AppUser superUser = new AppUser();
        superUser.setEmail(login);
        superUser.setPassword(hashPassword(password));
        superUser.setEnable(true);
        superUser.getAppUserRole().add(appUserRoleRepository.findByRole("ROLE_SUPER_USER"));
        AppUserData appUserData = new AppUserData();
        appUserData.setPhoneNumber("111222333");
        appUserData.setFirstName("Jan");
        appUserData.setLastName("Kowalski");
        superUser.setUserData(appUserData);
        appUserRepository.save(superUser);
    }

    @Override
    public void createTempAdminAccount(String login, String password) {
        AppUser admin = new AppUser();
        admin.setEmail(login);
        admin.setPassword(hashPassword(password));
        admin.setEnable(true);
        admin.getAppUserRole().add(appUserRoleRepository.findByRole("ROLE_ADMIN"));
        AppUserData appUserData = new AppUserData();
        appUserData.setPhoneNumber("333222111");
        appUserData.setFirstName("Marek");
        appUserData.setLastName("Maj");
        admin.setUserData(appUserData);
        appUserRepository.save(admin);
    }

    public List<AppUser> findAllUserWithGuestRole() {
        return Optional.ofNullable(appUserRepository.findByAppUserRole(appUserRoleRepository.findByRole("ROLE_GUEST"))
        ).orElse(Collections.emptyList());
    }

    public List<AppUser> findAllUserWithUserRole() {
        return Optional.ofNullable(appUserRepository.findByAppUserRole(appUserRoleRepository.findByRole("ROLE_USER"))
        ).orElse(Collections.emptyList());
    }

    public List<AppUser> findAllUserWithSuperUserRole() {
        return Optional.ofNullable(appUserRepository.findByAppUserRole(appUserRoleRepository.findByRole("ROLE_SUPER_USER"))
        ).orElse(Collections.emptyList());
    }

    public List<AppUser> findAllUserWithAdminRole() {
        return Optional.ofNullable(appUserRepository.findByAppUserRole(appUserRoleRepository.findByRole("ROLE_ADMIN"))
        ).orElse(Collections.emptyList());
    }

    @Override
    public void deleteUser(long userId) {
        if (appUserRepository.exists(userId)) {
            AppUser appUser = appUserRepository.findById(userId);
            appUser.getAppUserRole().forEach(role -> {
                if (role.getRole().equals("ROLE_USER") || role.getRole().equals("ROLE_GUEST")) {
                    Flat flat = flatService.findBySecretCode(appUser.getSecretFlatCode());
                    flatService.reservedFlat(flat, false);
                }

                if (role.getRole().equals("ROLE_SUPER_USER")) {
                    List<Block> blockList = blockService.findAllByAdministratorId(appUser.getId());
                    blockList.forEach(block -> {
                        block.setAdministrator(null);
                        blockService.save(block);
                    });
                }
            });

            appUserRepository.delete(userId);
        }
    }

    @Override
    public AppUser findById(long userId) {
        return appUserRepository.findById(userId);
    }

}


