package pl.dmcs.rkotas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmcs.rkotas.domain.*;
import pl.dmcs.rkotas.domain.charges.*;
import pl.dmcs.rkotas.util.PaymentStatus;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class RestInitServiceImpl implements RestInitService {

    private final BlockService blockService;
    private final AppUserService appUserService;

    @Autowired
    public RestInitServiceImpl(BlockService blockService, AppUserService appUserService) {
        this.blockService = blockService;
        this.appUserService = appUserService;
    }

    @Override
    public Block initBlock1() {
        Block block = new Block();
        BlockAddress address = BlockAddress.builder()
                .city("Warta")
                .postalCode("98-290")
                .street("Górna")
                .country("Polska")
                .blockNumber("10")
                .build();

        return getBlock(block, address, generateBaseRates());
    }

    @Override
    public Block initBlock2() {

        Block block = new Block();
        BlockAddress address = BlockAddress.builder()
                .city("Lodz")
                .postalCode("93-590")
                .street("Al. Politechniki")
                .blockNumber("9a")
                .country("Polska")
                .build();

        return getBlock(block, address, generateBaseRates());
    }

    private Rates generateBaseRates() {
        Rates rates = new Rates();
        rates.setLocaleData(LocalDateTime.now());
        rates.setColdWaterRate(5);
        rates.setElectricityRate(10);
        rates.setHeatingRate(15);
        rates.setHotWaterRate(10);
        rates.setRepairFundRate(20);
        return rates;
    }

    private Bill generateBill() {
        Bill bill = new Bill();
        bill.setLocaleData(LocalDateTime.now());
        bill.setElectricityList(new Electricity());
        bill.setColdWater(new ColdWater());
        bill.setHeating(new Heating());
        bill.setHotWater(new HotWater());
        bill.setRepairFund(new RepairFund());
        bill.setPayment(true);
        bill.setPaymentStatus(PaymentStatus.ACCEPTED);
        return bill;
    }

    private Set<Bill> generateBillList() {
        Set<Bill> billList = new HashSet<>();
        billList.add(generateBill());
        return billList;
    }

    private Block getBlock(Block block, BlockAddress address, Rates rates) {
        block.setBlockAddress(address);

        Set<Flat> flatList = new HashSet<>();
        flatList.add(generateFlat(0, 40, address, UUID.randomUUID().toString(), generateBillList(), rates));
        flatList.add(generateFlat(1, 60, address, UUID.randomUUID().toString(), generateBillList(), rates));
        flatList.add(generateFlat(2, 80, address, UUID.randomUUID().toString(), generateBillList(), rates));
        flatList.add(generateFlat(3, 100, address, UUID.randomUUID().toString(), generateBillList(), rates));
        flatList.add(generateFlat(4, 100, address, UUID.randomUUID().toString(), generateBillList(), rates));
        flatList.add(generateFlat(5, 100, address, UUID.randomUUID().toString(), generateBillList(), rates));
        flatList.add(generateFlat(6, 100, address, UUID.randomUUID().toString(), generateBillList(), rates));
        flatList.add(generateFlat(7, 100, address, UUID.randomUUID().toString(), generateBillList(), rates));
        flatList.add(generateFlat(8, 100, address, UUID.randomUUID().toString(), generateBillList(), rates));
        flatList.add(generateFlat(9, 100, address, UUID.randomUUID().toString(), generateBillList(), rates));
        flatList.add(generateFlat(10, 100, address, UUID.randomUUID().toString(), generateBillList(), rates));

        block.setAdministrator(appUserService.findByEmail("super@gmail.com"));
        block.setFlats(flatList);
        return blockService.save(block);
    }

    private Flat generateFlat(int localeNumber, int flatArea,
                              BlockAddress address, String secretCode, Set<Bill> billList, Rates rates) {
        return Flat.builder()
                .flatArea(flatArea)
                .localeNumber(localeNumber)
                .blockAddress(address)
                .secretCode(secretCode)
                .bills(billList)
                .rates(rates)
                .build();
    }

}
