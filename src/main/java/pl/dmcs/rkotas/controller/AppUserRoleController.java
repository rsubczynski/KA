package pl.dmcs.rkotas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dmcs.rkotas.domain.AppUserRole;
import pl.dmcs.rkotas.service.AppUserRoleService;

import javax.validation.Valid;

@Controller
public class AppUserRoleController {

    private AppUserRoleService appUserRoleService;

    @Autowired
    public AppUserRoleController(AppUserRoleService appUserRoleService) {
        this.appUserRoleService = appUserRoleService;
    }

    @RequestMapping(value="/appUserRole")
    public String showUserRole(Model model) {
        model.addAttribute("appUserRole", new AppUserRole());

        AppUserRole appUserRoleUser = new AppUserRole();
        AppUserRole appUserRoleAdmin = new AppUserRole();
        AppUserRole appUserRoleSuperUser = new AppUserRole();
        AppUserRole appUserRoleGuest = new AppUserRole();

        appUserRoleUser.setRole("USER");
        appUserRoleSuperUser.setRole("SUPER_USER");
        appUserRoleAdmin.setRole("ADMIN");
        appUserRoleGuest.setRole("GUEST");
        appUserRoleService.addAppUserRole(appUserRoleUser);
        appUserRoleService.addAppUserRole(appUserRoleSuperUser);
        appUserRoleService.addAppUserRole(appUserRoleAdmin);
        appUserRoleService.addAppUserRole(appUserRoleGuest);

        return "appUserRole";
    }

    @RequestMapping(value = "/addAppUserRole", method = RequestMethod.POST)
    public String addUserRole(@Valid @ModelAttribute("appUserRole") AppUserRole appUserRole, BindingResult result) {
        appUserRoleService.addAppUserRole(appUserRole);
        return "redirect:appUsers.html";
    }
}

