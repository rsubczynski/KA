package pl.dmcs.rkotas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.rkotas.authentication.AuthenticationFacade;
import pl.dmcs.rkotas.domain.AppUser;
import pl.dmcs.rkotas.domain.Bill;
import pl.dmcs.rkotas.domain.Rates;
import pl.dmcs.rkotas.dto.EditUserForm;
import pl.dmcs.rkotas.dto.MeterForm;
import pl.dmcs.rkotas.service.AppUserService;
import pl.dmcs.rkotas.util.AppDataFormatter;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final AuthenticationFacade authenticationFacade;
    private final AppUserService appUserService;

    public UserController(AuthenticationFacade authenticationFacade, AppUserService appUserService) {
        this.authenticationFacade = authenticationFacade;
        this.appUserService = appUserService;
    }

    @GetMapping(value = {"/data"})
    public String data(@RequestParam(value = "page", required = false, defaultValue = "0") String page, Model model) {

        AppUser appUser = appUserService.findByEmail(
                authenticationFacade.getLoginUser().getUsername());
        List<Bill> billList = new ArrayList<>(appUser.getUserData().getFlat().getBills());
        model.addAttribute("user", appUser);
        model.addAttribute("bill", new Bill());

        int parsePAge = 0;

        try {
            parsePAge = Integer.parseInt(page);
        } catch (NumberFormatException e) {
            model.addAttribute("pageError", true);
            return "userData";
        }

        if (parsePAge < 0 || parsePAge + 1 > billList.size()) {
            model.addAttribute("pageError", true);
            return "userData";
        }

        model.addAttribute("data", AppDataFormatter.getFormattedDate(billList.get(parsePAge).getLocaleData()));
        model.addAttribute("bill", billList.get(parsePAge));

        if (parsePAge + 1 < billList.size())
            model.addAttribute("nextPage", parsePAge + 1);

        if (parsePAge > 0) {
            model.addAttribute("previouslyPage", parsePAge - 1);
        }
        return "userData";
    }


    @GetMapping(value = {"/edit"})
    private String editUser(Model model) {
        AppUser appUser = appUserService.findByEmail(
                authenticationFacade.getLoginUser().getUsername());

        EditUserForm editUserForm = new EditUserForm();
        editUserForm.setFirstName(appUser.getUserData().getFirstName());
        editUserForm.setLastName(appUser.getUserData().getLastName());
        editUserForm.setTelephone(appUser.getUserData().getPhoneNumber());
        model.addAttribute("editUserForm", editUserForm);

        return "editUser";
    }

    @PostMapping(value = {"/edit"})
    public String addDataToUser(@Valid @ModelAttribute("editUserForm") EditUserForm editUserForm, BindingResult result) {
        if (result.hasErrors()) {
            return "editUser";
        }
        appUserService.editUserData(authenticationFacade.getLoginUser().getUsername(), editUserForm);

        return "redirect:/user/data";
    }


    @GetMapping(value = {"/meter"})
    public String meter(Model model) {
        AppUser appUser = appUserService.findByEmail(
                authenticationFacade.getLoginUser().getUsername());
        model.addAttribute("meterForm", new MeterForm());
        model.addAttribute("rates", appUser.getUserData().getFlat().getRates());
        model.addAttribute("date",
                AppDataFormatter.getFormattedDate(appUser.getUserData().getFlat().getRates().getLocaleData()));
        return "userMeterReading";
    }

    @PostMapping(value = {"/meter"})
    public String meter(@Valid @ModelAttribute("meterForm") MeterForm meterForm, BindingResult result, Model model) {
        AppUser appUser = appUserService.findByEmail(
                authenticationFacade.getLoginUser().getUsername());
        Rates rates = appUser.getUserData().getFlat().getRates();
        model.addAttribute("rates", rates);
        model.addAttribute("date",
                AppDataFormatter.getFormattedDate(appUser.getUserData().getFlat().getRates().getLocaleData()));

        if (result.hasErrors()) {
            return "userMeterReading";

        }
        appUserService.addMeterToUser(appUser, meterForm, rates);
        return "redirect:/user/data";
    }

    @GetMapping(value = {"/payment"})
    public String payment() {
        return "userPayment";
    }
}
