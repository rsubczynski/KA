package pl.dmcs.rkotas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping(value = {"/data"})
    public String data() {
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
