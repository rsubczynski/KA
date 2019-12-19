package pl.dmcs.rkotas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.rkotas.domain.AppUser;
import pl.dmcs.rkotas.domain.Flat;
import pl.dmcs.rkotas.dto.RegisterForm;
import pl.dmcs.rkotas.service.AppUserService;
import pl.dmcs.rkotas.service.FlatService;
import pl.dmcs.rkotas.service.common.EmailService;
import pl.dmcs.rkotas.service.common.ReCaptchaService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private ReCaptchaService reCaptchaService;
    private EmailService emailService;
    private AppUserService userService;
    private FlatService flatService;

    @Autowired
    public RegisterController(ReCaptchaService reCaptchaService, EmailService emailService, AppUserService userService,
                              FlatService flatService) {
        this.reCaptchaService = reCaptchaService;
        this.emailService = emailService;
        this.userService = userService;
        this.flatService = flatService;
    }

    @GetMapping(value = {"", "/"})
    public String register(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @PostMapping(value = {"", "/"})
    public String register(@Valid @ModelAttribute("registerForm") RegisterForm registerForm, BindingResult result,
                           HttpServletRequest request, Model model) {

        String response = request.getParameter("g-recaptcha-response");

        if (result.hasErrors() || !reCaptchaService.verify(response) || flatService.findFreeFlats().size() == 0) {
            if (!reCaptchaService.verify(response)) {
                model.addAttribute("captchaError", true);
            }

            if (flatService.findFreeFlats().size() == 0) {
                model.addAttribute("notFoundFreeFlat", true);
            }

            return "register";
        }

        Flat flat =  flatService.findFreeFlats().get(0);
        AppUser createdUser = userService.addUseAfterRegister(registerForm ,flatService.reservedFlat(flat));
        emailService.sendMessageToUser(createdUser, request);

        return "login";
    }
}
