package pl.dmcs.rkotas.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dmcs.rkotas.domain.*;
import pl.dmcs.rkotas.domain.charges.Electricity;
import pl.dmcs.rkotas.service.AppUserService;
import pl.dmcs.rkotas.service.BlockService;

import java.util.*;

@RestController
@RequestMapping(value ="/rest")
public class RestTestController {

    private AppUserService appUserService;
    private BlockService blockService;

    public RestTestController(AppUserService appUserService, BlockService blockService) {
        this.appUserService = appUserService;
        this.blockService = blockService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    private AppUser test() {
        AppUser appUser = appUserService.findByEmail("radoslaw.subczynski@gmail.com");

//        appUser.setFlat();

        return appUserService.findByEmail("radoslaw.subczynski@gmail.com");
    }

    @GetMapping(value = "/addBlock", produces = MediaType.APPLICATION_JSON_VALUE)
    private void addBlock() {

    }

}