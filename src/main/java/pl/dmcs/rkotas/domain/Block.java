package pl.dmcs.rkotas.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="block")
public class Block implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private AppUser administrator;

    @OneToOne(cascade = {CascadeType.ALL})
    private BlockAddress blockAddress;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Flat> flats = new HashSet<Flat>(0);
}
