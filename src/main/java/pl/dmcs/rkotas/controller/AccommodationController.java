package pl.dmcs.rkotas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dmcs.rkotas.dto.AccommodationForm;

import javax.validation.Valid;

@Controller
@RequestMapping("/accommodation")
public class AccommodationController {

    @RequestMapping( value = {"", "/"})
    public String showAppUsers(Model model) {

        model.addAttribute("accommodationForm", new AccommodationForm());
        return "accommodation";
    }

    @PostMapping( value = {"", "/"})
    public String register(@Valid @ModelAttribute("accommodationForm") AccommodationForm accommodationForm, BindingResult result){
        if (result.hasErrors()) {
            return "accommodation";
        }

        return "dashboard";

    }
}
