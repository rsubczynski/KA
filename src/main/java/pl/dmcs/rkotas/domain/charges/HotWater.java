package pl.dmcs.rkotas.domain.charges;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="hotwater")
public class HotWater {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private double count;

    private double price;

    private double rate;
}
