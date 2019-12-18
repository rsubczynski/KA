package pl.dmcs.rkotas.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="appuserdata")
public class AppUserData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    @OneToOne(cascade = {CascadeType.ALL})
    private Flat flat;
}
