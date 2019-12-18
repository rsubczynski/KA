package pl.dmcs.rkotas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.rkotas.domain.AppUser;
import pl.dmcs.rkotas.dto.RegisterForm;
import pl.dmcs.rkotas.service.AppUserService;
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

    public RegisterController(ReCaptchaService reCaptchaService, EmailService emailService, AppUserService userService) {
        this.reCaptchaService = reCaptchaService;
        this.emailService = emailService;
        this.userService = userService;
    }

    @GetMapping( value = {"", "/"})
    public String register(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @PostMapping( value = {"", "/"})
    public String register(@Valid @ModelAttribute("registerForm") RegisterForm registerForm, BindingResult result,
                           HttpServletRequest request, Model model) {

        String response = request.getParameter("g-recaptcha-response");

        if (result.hasErrors() || !reCaptchaService.verify(response)) {
            if (!reCaptchaService.verify(response)) {
                model.addAttribute("captchaError", true);
            }
            return "register";
        }
        AppUser createdUser = userService.addUseAfterRegister(registerForm);
        emailService.sendSimpleMessage(createdUser,request);
        return "login";

    }
}
