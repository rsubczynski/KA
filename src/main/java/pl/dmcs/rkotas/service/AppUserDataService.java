package pl.dmcs.rkotas.service;

import pl.dmcs.rkotas.domain.AppUserData;

public interface AppUserDataService {

    AppUserData findByFlatId(long flatId);
}
