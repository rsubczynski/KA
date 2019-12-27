package pl.dmcs.rkotas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.rkotas.dao.AppUserDataRepository;
import pl.dmcs.rkotas.domain.AppUserData;

@Transactional
@Service
public class AppUserDataServiceImpl implements AppUserDataService {

    private final AppUserDataRepository appUserDataRepository;

    public AppUserDataServiceImpl(AppUserDataRepository appUserDataRepository) {
        this.appUserDataRepository = appUserDataRepository;
    }


    public AppUserData findByFlatId(long flatId){
        return appUserDataRepository.findByFlat_Id(flatId);
    }
}
