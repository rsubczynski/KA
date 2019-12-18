package pl.dmcs.rkotas.domain.charges;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="heating")
public class Heating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private double count;

    private double price;

    private double rate;
}