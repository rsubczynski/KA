package pl.dmcs.rkotas.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="flat")
public class Flat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private double flatArea;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Bill> bills = new HashSet<Bill>(0);

    @ManyToOne
    private BlockAddress blockAddress;

    @NotNull
    private long localeNumber;

    private String secretCode;

    private boolean isReserved;

    @ManyToOne(cascade = CascadeType.ALL)
    private Rates rates;

}
