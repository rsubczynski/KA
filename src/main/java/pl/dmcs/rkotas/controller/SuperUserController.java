package pl.dmcs.rkotas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.dmcs.rkotas.authentication.IAuthenticationFacade;
import pl.dmcs.rkotas.domain.AppUser;
import pl.dmcs.rkotas.domain.Bill;
import pl.dmcs.rkotas.domain.Block;
import pl.dmcs.rkotas.domain.Flat;
import pl.dmcs.rkotas.service.AppUserDataService;
import pl.dmcs.rkotas.service.AppUserService;
import pl.dmcs.rkotas.service.BillService;
import pl.dmcs.rkotas.service.BlockService;
import pl.dmcs.rkotas.util.AppDataFormatter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/superUser")
public class SuperUserController {

    private final IAuthenticationFacade authenticationFacade;
    private final AppUserService appUserService;
    private final BlockService blockService;
    private final BillService billService;
    private final AppDataFormatter appDataFormatter;
    private final AppUserDataService appUserDataService;

    @Autowired
    public SuperUserController(IAuthenticationFacade authenticationFacade, AppUserService appUserService,
                               BlockService blockService, BillService billService, AppDataFormatter appDataFormatter, AppUserDataService appUserDataService) {
        this.authenticationFacade = authenticationFacade;
        this.appUserService = appUserService;
        this.blockService = blockService;
        this.billService = billService;
        this.appDataFormatter = appDataFormatter;
        this.appUserDataService = appUserDataService;
    }

    @GetMapping(value = {"/serviceFees"})
    public String serviceFees(@RequestParam(value = "page", required = false, defaultValue = "0") String page, Model model) {
        AppUser appUser = appUserService.findByEmail(
                authenticationFacade.getLoginUser().getUsername());

        List<Block> blockList = blockService.findAllByAdministratorId(appUser.getId());
        model.addAttribute("userDataService", appUserDataService);
        int parsePage;

        try {
            parsePage = Integer.parseInt(page);
        } catch (NumberFormatException e) {
            model.addAttribute("pageError", true);
            model.addAttribute("block", new Block());
            model.addAttribute("flats", new Flat());
            return "serviceFees";
        }

        if (parsePage < 0 || parsePage + 1 > blockList.size()) {
            model.addAttribute("pageError", true);
            model.addAttribute("block", new Block());
            model.addAttribute("flats", new Flat());
            return "serviceFees";
        }


        model.addAttribute("block", blockList.get(parsePage));
        model.addAttribute("flats", blockList.get(parsePage).getFlats().stream()
                .sorted(Comparator.comparingLong(Flat::getLocaleNumber))
                .collect(Collectors.toList()));

        if (parsePage + 1 < blockList.size())
            model.addAttribute("nextPage", parsePage + 1);

        if (parsePage > 0) {
            model.addAttribute("previouslyPage", parsePage - 1);
        }

        return "serviceFees";
    }

    @GetMapping(value = {"/payment"})
    String payment(@RequestParam(value = "flat") String flatId, Model model) {

        List<Bill> billList = billService.getListBillForFlat(Long.parseLong(flatId));
        model.addAttribute("bills", billList);
        model.addAttribute("formatter", appDataFormatter);

        return "payment";
    }

    @GetMapping(value = {"/acceptedBill"})
    String acceptedBill(@RequestParam(value = "bill") String billId) {
        billService.acceptedBill(Long.parseLong(billId));
        return "redirect:/superUser/serviceFees";
    }

    @GetMapping(value = {"/rejectedBill"})
    String rejectedBill(@RequestParam(value = "bill") String billId) {
        billService.rejectedBill(Long.parseLong(billId));
        return "redirect:/superUser/serviceFees";
    }

}
