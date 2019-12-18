package pl.dmcs.rkotas.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.rkotas.domain.AppUser;

@Transactional 
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findById(long id);
    AppUser findByEmail(String login);
    boolean existsByUidUser(long uid);
    AppUser findByUidUser(long uid);
    
}

