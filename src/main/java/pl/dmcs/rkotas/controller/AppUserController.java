package pl.dmcs.rkotas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.rkotas.domain.AppUser;
import pl.dmcs.rkotas.service.AppUserService;
import pl.dmcs.rkotas.service.common.EmailService;
import pl.dmcs.rkotas.validator.AppUserValidator;
import javax.servlet.http.HttpServletRequest;
import javax.validation.*;

@Controller
public class AppUserController {

    private AppUserValidator appUserValidator = new AppUserValidator();

    private AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @RequestMapping("/")
    public String home(){
        return "login";
    }

    @RequestMapping(value = "/appUsers")
    public String showAppUsers(Model model, HttpServletRequest request) {

        int appUserId = ServletRequestUtils.getIntParameter(request, "appUserId" , -1);

	    if (appUserId > 0)
            model.addAttribute("appUser", appUserService.getAppUser(appUserId));
	    else
            model.addAttribute("appUser", new AppUser());

        model.addAttribute("appUserList", appUserService.listAppUser());

	 return "appUser";
    }

    @RequestMapping(value = "/addAppUser", method = RequestMethod.POST)
    public String addAppUser(@Valid @ModelAttribute("appUser") AppUser appUser, BindingResult result, Model model) {

        appUserValidator.validate(appUser, result);

        if (result.getErrorCount() == 0) {
            if (appUser.getId()==0)
                appUserService.addAppUser(appUser);
            else
                appUserService.editAppUser(appUser);

            return "redirect:appUsers.html";
        }

        model.addAttribute("appUserList", appUserService.listAppUser());
        return "appUser";
    }

    @RequestMapping("/delete/{appUserId}")
    public String deleteUser(@PathVariable("appUserId") Long appUserId) {
        appUserService.removeAppUser(appUserId);
        return "redirect:/appUsers.html";
   }

}

