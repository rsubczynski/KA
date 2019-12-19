package pl.dmcs.rkotas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.dmcs.rkotas.authentication.AuthenticationFacade;
import pl.dmcs.rkotas.domain.AppUser;
import pl.dmcs.rkotas.domain.Bill;
import pl.dmcs.rkotas.service.AppUserService;

import java.text.SimpleDateFormat;
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
    public String data(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page, Model model) {
        AppUser appUser = appUserService.findByEmail(
                authenticationFacade.getLoginUser().getUsername());
        List<Bill> billList = new ArrayList<>(appUser.getUserData().getFlat().getBills());

        if(page <0 || page> billList.size()){
            //TODO : ZABEZPIECZ TO!
            System.out.println("Zaraz cos jebnie! :)");
        }
        model.addAttribute("localDateTimeFormat", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"));
        model.addAttribute("user", appUser);
        model.addAttribute("bill", billList.get(page));

        if (page + 1 < billList.size())
            model.addAttribute("nextPage", page + 1);

        if (page > 0) {
            model.addAttribute("previouslyPage", page - 1);
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
