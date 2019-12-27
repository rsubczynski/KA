package pl.dmcs.rkotas.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.rkotas.domain.AppUser;
import pl.dmcs.rkotas.domain.AppUserRole;

import java.util.List;

@Transactional
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByEmail(String login);

    boolean existsByUidUser(long uid);

    boolean existsByEmail(String email);

    AppUser findByUidUser(long uid);

    List<AppUser> findByAppUserRole(AppUserRole role);

}

