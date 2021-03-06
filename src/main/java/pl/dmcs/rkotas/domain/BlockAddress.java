package pl.dmcs.rkotas.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="blockAddress")
public class BlockAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String street;

    private String country;

    private String blockNumber;

    private String city;

    private String postalCode;
}
