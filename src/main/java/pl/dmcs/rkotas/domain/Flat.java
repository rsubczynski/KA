package pl.dmcs.rkotas.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="flat")
public class Flat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private double flatArea;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Bill> bills = new HashSet<Bill>(0);

    @ManyToOne
    private BlockAddress blockAddress;

    @NotNull
    private long localeNumber;

    private String secretCode;


}