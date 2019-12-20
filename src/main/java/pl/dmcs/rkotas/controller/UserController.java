package pl.dmcs.rkotas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.dmcs.rkotas.authentication.AuthenticationFacade;
import pl.dmcs.rkotas.domain.AppUser;
import pl.dmcs.rkotas.domain.Bill;
import pl.dmcs.rkotas.service.AppUserService;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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

        if (parsePAge < 0 || parsePAge +1 > billList.size()) {
            model.addAttribute("pageError", true);
            return "userData";
        }

        model.addAttribute("data", DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                .format(billList.get(parsePAge).getLocaleData()));
        model.addAttribute("bill", billList.get(parsePAge));

        if (parsePAge + 1 < billList.size())
            model.addAttribute("nextPage", parsePAge + 1);

        if (parsePAge > 0) {
            model.addAttribute("previouslyPage", parsePAge - 1);
        }
        return "userData";
    }

    @GetMapping(value = {"/meter"})
    public String meter() {
        return "userMeterReading";
    }

    @GetMapping(value = {"/payment"})
    public String payment() {
        return "userPayment";
    }
}
