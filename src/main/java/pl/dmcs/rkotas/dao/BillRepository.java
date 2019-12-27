package pl.dmcs.rkotas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.dmcs.rkotas.domain.Bill;

import java.util.List;


@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    @Query(value = "SELECT * FROM Bill bill INNER JOIN flat_bill ON bill.id = flat_bill.bills_id " +
            "where flat_id = ?1 order by localedata DESC;", nativeQuery = true)
    List<Bill> getSortedBillListForFlat(long flatId);

    Bill findById(Long id);
}
