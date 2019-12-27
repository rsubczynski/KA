package pl.dmcs.rkotas.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dmcs.rkotas.domain.*;
import pl.dmcs.rkotas.service.AppUserRoleService;
import pl.dmcs.rkotas.service.AppUserService;
import pl.dmcs.rkotas.service.RestInitService;


@RestController
@RequestMapping(value ="/rest")
public class RestTestController {

    private AppUserService appUserService;
    private RestInitService restInitService;

    public RestTestController(AppUserService appUserService, RestInitService restInitService) {
        this.appUserService = appUserService;
        this.restInitService = restInitService;
    }

    @GetMapping(value = "/addBlock", produces = MediaType.APPLICATION_JSON_VALUE)
    private void addBlock() {
        appUserService.createTempAdminAccount("admin@gmail.com", "Admin");
        appUserService.createTempSuperUserAccount("super@gmail.com", "Super");
        restInitService.initBlock1();
        restInitService.initBlock2();
    }
}