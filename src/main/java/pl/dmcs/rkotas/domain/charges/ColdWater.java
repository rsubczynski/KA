package pl.dmcs.rkotas.domain.charges;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="coldwater")
public class ColdWater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double count;

    private double price;

    private double rate;
}
