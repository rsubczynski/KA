package pl.dmcs.rkotas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dmcs.rkotas.service.AppUserService;
import pl.dmcs.rkotas.service.BlockService;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AppUserService appUserService;
    private final BlockService blockService;

    @Autowired
    public AdminController(AppUserService appUserService, BlockService blockService) {
        this.appUserService = appUserService;
        this.blockService = blockService;
    }

    @RequestMapping("/users")
    public String showAppUsers(Model model) {
        model.addAttribute("guestList", appUserService.findAllUserWithGuestRole());
        model.addAttribute("userList", appUserService.findAllUserWithUserRole());
        model.addAttribute("superUserList", appUserService.findAllUserWithSuperUserRole());
        model.addAttribute("adminList", appUserService.findAllUserWithAdminRole());
        model.addAttribute("blockService", blockService);
        return "users";
    }

    @GetMapping("/users/{id}")
    public String deleteUser(@PathVariable(value = "id") String id) {
        appUserService.deleteUser(Long.parseLong(id));
        return "redirect:/admin/users";
    }

    @RequestMapping("/buildings")
    public String building(Model model) {
        return "buildings";
    }
}
