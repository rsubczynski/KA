package pl.dmcs.rkotas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.rkotas.domain.Flat;


@Transactional
@Repository
public interface FlatRepository extends JpaRepository<Flat, Long> {

    Flat findBySecretCode(String secretCode);

    Flat findById(long id);

}
