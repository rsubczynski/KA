package pl.dmcs.rkotas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.rkotas.dao.FlatRepository;
import pl.dmcs.rkotas.domain.Flat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class FlatServiceImpl implements FlatService {

    private final FlatRepository flatRepository;

    @Autowired
    public FlatServiceImpl(FlatRepository flatRepository) {
        this.flatRepository = flatRepository;
    }

    @Override
    public List<Flat> findFreeFlats() {
        return Optional.ofNullable(flatRepository.findAll()).orElse(new ArrayList<>())
                .stream()
                .filter(flat -> !flat.isReserved())
                .collect(Collectors.toList());
    }

    @Override
    public Flat reservedFlat(Flat flat, boolean reserved) {
        flat.setReserved(reserved);
        return flatRepository.save(flat);
    }

    @Override
    public Flat findBySecretCode(String secretCode) {
        return flatRepository.findBySecretCode(secretCode);
    }

    @Override
    public Flat findById(long id) {
        return flatRepository.findById(id);
    }
}
