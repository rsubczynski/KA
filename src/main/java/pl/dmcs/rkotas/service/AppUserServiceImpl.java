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
    public void addMeterToUser(AppUser appUser, MeterForm meterForm, Rates repairFundRate) {
        ColdWater coldWater = ColdWater.builder()
                .rate(repairFundRate.getColdWaterRate())
                .count(Double.parseDouble(meterForm.getColdWater()))
                .price(repairFundRate.getColdWaterRate() * Double.parseDouble(meterForm.getColdWater()))
                .build();

        HotWater hotWater = HotWater.builder()
                .rate(repairFundRate.getHotWaterRate())
                .count(Double.parseDouble(meterForm.getHotWater()))
                .price(repairFundRate.getHotWaterRate() * Double.parseDouble(meterForm.getHotWater()))
                .build();

        Electricity electricity = Electricity.builder()
                .rate(repairFundRate.getElectricityRate())
                .count(Double.parseDouble(meterForm.getElectricity()))
                .price(repairFundRate.getElectricityRate() * Double.parseDouble(meterForm.getElectricity()))
                .build();

        Heating heating = Heating.builder()
                .rate(repairFundRate.getHeatingRate())
                .count(Double.parseDouble(meterForm.getHeating()))
                .price(repairFundRate.getHeatingRate() * Double.parseDouble(meterForm.getHeating()))
                .build();

        RepairFund repairFund = RepairFund.builder()
                .rate(repairFundRate.getRepairFundRate())
                .count(1)
                .price(repairFundRate.getRepairFundRate())
                .build();

        Bill bill = new Bill();
        bill.setColdWater(coldWater);
        bill.setHotWater(hotWater);
        bill.setElectricityList(electricity);
        bill.setHeating(heating);
        bill.setRepairFund(repairFund);

        bill.setPayment(false);
        bill.setTotalCount(coldWater.getPrice() + hotWater.getPrice() +
                electricity.getPrice() + heating.getPrice() + repairFund.getPrice());

        appUser.getUserData().getFlat().getBills().add(bill);

        appUserRepository.save(appUser);
    }

}


