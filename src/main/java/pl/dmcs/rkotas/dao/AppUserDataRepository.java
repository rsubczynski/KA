package pl.dmcs.rkotas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.rkotas.domain.AppUserData;

@Repository
public interface AppUserDataRepository extends JpaRepository<AppUserData, Long> {

    AppUserData findByFlat_Id(long flatId);
}
