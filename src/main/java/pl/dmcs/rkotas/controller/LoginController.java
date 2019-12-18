package pl.dmcs.rkotas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.dmcs.rkotas.service.AppUserService;

@Controller
public class LoginController {

    private final AppUserService appUserService;

    LoginController(
    AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @RequestMapping("/")
    public String home(){
        return "login";
    }

    @RequestMapping(value = "/login")
    public String customLogin(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout,
                              Model model) {

        if (error != null) {
            model.addAttribute("error", true);
        }

        if (logout != null) {
            model.addAttribute("msg", true);
        }

        return "login";
    }

    @RequestMapping(value = "/active")
    public String activeAccount(@RequestParam(value = "userUid") long uid, Model model) {
        boolean isUserActive = appUserService.activateUser(uid);
        if(isUserActive){
            model.addAttribute("accountActive", true);
        }else{
            model.addAttribute("accountNotFound", true);
        }

        return "login";
    }

    @RequestMapping(value = "/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

}


