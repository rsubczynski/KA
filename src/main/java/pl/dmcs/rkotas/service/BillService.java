package pl.dmcs.rkotas.service;

import org.springframework.stereotype.Service;
import pl.dmcs.rkotas.domain.Bill;

import java.util.List;

@Service
public interface BillService {
    List<Bill> getListBillForFlat(Long id);

    void payBill(Long billId);

    void acceptedBill(Long billId);

    void rejectedBill(Long billId);
}
