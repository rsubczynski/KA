package pl.dmcs.rkotas.service;

import pl.dmcs.rkotas.domain.AppUserRole;
import java.util.List;

public interface AppUserRoleService {

    void addAppUserRole(AppUserRole appUserRole);
    List<AppUserRole> listAppUserRole();
    AppUserRole getAppUserRole(long id);

}

