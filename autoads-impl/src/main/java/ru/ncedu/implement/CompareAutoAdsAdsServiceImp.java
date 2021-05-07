package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.CompareAuto;
import ru.ncedu.repositoryes.CompareAutoRepository;
import ru.ncedu.services.CompareAutoAdsService;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompareAutoAdsAdsServiceImp implements CompareAutoAdsService {

    private final CompareAutoRepository compareAutoRepository;

    @Override
    public CompareAuto findCompareAutoAdsByIdAuto(Long idAuto, Long idUser) {
        CompareAuto compareAuto = compareAutoRepository.findCompareAutoAdsByIdUserAndIdAuto(idUser, idAuto);
        return (compareAuto != null) ? compareAuto : null;
    }
}
