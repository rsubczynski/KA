package pl.dmcs.rkotas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.rkotas.domain.Block;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {

    List<Block> findByAdministrator_Id(long id);

}
