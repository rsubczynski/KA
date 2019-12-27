package pl.dmcs.rkotas.domain;

import lombok.Getter;
import lombok.Setter;
import pl.dmcs.rkotas.domain.charges.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="rates")
public class Rates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime localeData;

    private double electricityRate;

    private double coldWaterRate;

    private double heatingRate;

    private double hotWaterRate;

    private double repairFundRate;

}
