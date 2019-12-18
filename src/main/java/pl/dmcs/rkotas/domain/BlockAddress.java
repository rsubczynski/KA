package pl.dmcs.rkotas.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="blockAddress")
public class BlockAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String street;

    private String country;

    private String city;

    private String postalCode;
}
