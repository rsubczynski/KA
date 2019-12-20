package pl.dmcs.rkotas.domain;

import lombok.Getter;
import lombok.Setter;
import pl.dmcs.rkotas.domain.charges.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDateTime localeData;

    @OneToOne(cascade = CascadeType.ALL)
    private Electricity electricityList;

    @OneToOne(cascade = CascadeType.ALL)
    private ColdWater coldWater;

    @OneToOne(cascade = CascadeType.ALL)
    private Heating heating;

    @OneToOne(cascade = CascadeType.ALL)
    private HotWater hotWater;

    @OneToOne(cascade = CascadeType.ALL)
    private RepairFund repairFund;

    private double totalCount;

    private boolean isPayment;

}
