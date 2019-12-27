package pl.dmcs.rkotas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dmcs.rkotas.authentication.AuthenticationFacade;
import pl.dmcs.rkotas.dto.AccommodationForm;
import pl.dmcs.rkotas.service.AppUserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/accommodation")
public class AccommodationController {

    private final AppUserService appUserService;
    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public AccommodationController(AppUserService appUserService, AuthenticationFacade authenticationFacade) {
        this.appUserService = appUserService;
        this.authenticationFacade = authenticationFacade;
    }

    @RequestMapping( value = {"", "/"})
    public String showAppUsers(Model model) {

        model.addAttribute("accommodationForm", new AccommodationForm());
        return "accommodation";
    }

    @PostMapping( value = {"", "/"})
    public String addDataToUser(@Valid @ModelAttribute("accommodationForm") AccommodationForm accommodationForm, BindingResult result){
        if (result.hasErrors()) {
            return "accommodation";
        }
        appUserService.addDataToUser(authenticationFacade.getLoginUser().getUsername(), accommodationForm);
        SecurityContextHolder.clearContext();

        return "redirect:/login";

    }
}
