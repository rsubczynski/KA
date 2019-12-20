package pl.dmcs.rkotas.domain.charges;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="repeirfund")
public class RepairFund {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private double count;

    private double price;

    private double rate;


}
