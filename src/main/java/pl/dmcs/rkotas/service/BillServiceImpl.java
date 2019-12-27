package pl.dmcs.rkotas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.rkotas.dao.BillRepository;
import pl.dmcs.rkotas.domain.Bill;
import pl.dmcs.rkotas.util.PaymentStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;

    @Autowired
    public BillServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public List<Bill> getListBillForFlat(Long id) {
        return Optional.ofNullable(billRepository.getSortedBillListForFlat(id)).orElse(Collections.emptyList());
    }

    @Override
    public void payBill(Long billId) {
        if (billRepository.exists(billId)) {
            Bill bill = changeBillStatus(billId, PaymentStatus.WAITING);
            bill.setTotalCount(0);
            bill.setPayment(true);
            billRepository.save(bill);
        }
    }

    @Override
    public void acceptedBill(Long billId) {
        if (billRepository.exists(billId)) {
            Bill bill = changeBillStatus(billId, PaymentStatus.ACCEPTED);
            billRepository.save(bill);
        }
    }

    @Override
    public void rejectedBill(Long billId) {
        if (billRepository.exists(billId)) {
            Bill bill = changeBillStatus(billId, PaymentStatus.REJECTED);
            billRepository.save(bill);
        }
    }

    private Bill changeBillStatus(Long billId, PaymentStatus status) {
        Bill bill = billRepository.findById(billId);
        bill.setPaymentStatus(status);
        return bill;
    }
}
