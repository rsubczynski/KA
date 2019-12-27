package pl.dmcs.rkotas.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Getter
@Setter
@Entity
@Table(name="block")
public class Block implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private AppUser administrator;

    @OneToOne(cascade = {CascadeType.ALL})
    private BlockAddress blockAddress;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Flat> flats = new TreeSet<>(Comparator.comparingLong(Flat::getLocaleNumber));
}
