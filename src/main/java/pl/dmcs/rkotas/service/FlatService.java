package pl.dmcs.rkotas.service;

import pl.dmcs.rkotas.domain.Flat;

import java.util.List;

public interface FlatService {

    List<Flat> findFreeFlats();

    Flat reservedFlat(Flat flat);

    Flat findBySecretCode(String uid);

    Flat findById(long id);
}
