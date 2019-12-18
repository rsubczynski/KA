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
        AppUser appUser =appUserService.findByEmail("radoslaw.subczynski@gmail.com");

//        appUser.setFlat();

        return appUserService.findByEmail("radoslaw.subczynski@gmail.com");
    }

    @GetMapping(value = "/addBlock", produces = MediaType.APPLICATION_JSON_VALUE)
    private Block addBlock() {
        Block block1 = new Block();
        BlockAddress address = new BlockAddress();
        address.setCity("Warta");
        address.setCountry("Poland");
        address.setPostalCode("98-290");
        address.setStreet("Górna");

        block1.setBlockAddress(address);

        Set<Flat> flatList = new HashSet<>();
        Flat flat = new Flat();
        flat.setFlatArea(1);
        flat.setLocaleNumber(1);
        flat.setBlockAddress(address);
        flat.setSecretCode("dupa0");

        Set<Bill> bills = new HashSet<>();

        Bill bill = new Bill();
        Electricity electricity = new Electricity();
        electricity.setCount(10);
        electricity.setPrice(20);
        bill.setElectricityList(electricity);
        bill.setElectricityList(electricity);
        bill.setElectricityList(electricity);
        bill.setElectricityList(electricity);
        bill.setElectricityList(electricity);
        bills.add(bill);
        flat.setBills(bills);

        Flat flat1 = new Flat();
        flat1.setFlatArea(2);
        flat1.setLocaleNumber(2);
        flat1.setSecretCode("dupa1");
        flat1.setBlockAddress(address);

        Flat flat2 = new Flat();
        flat2.setFlatArea(3);
        flat2.setLocaleNumber(3);
        flat2.setSecretCode("dupa2");
        flat2.setBlockAddress(address);

        flatList.add(flat);
        flatList.add(flat1);
        flatList.add(flat2);

        block1.setAdministrator(appUserService.findByEmail("radoslaw.subczynski@gmail.com"));

        block1.setFlats(flatList);

        return blockService.save(block1);
    }
}