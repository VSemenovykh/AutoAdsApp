package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncedu.repositoryes.CompareAutoRepository;
import ru.ncedu.services.DeleteCompareAutoAdsService;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DeleteCompareAutoAdsServiceImp implements DeleteCompareAutoAdsService {

    private final CompareAutoRepository compareAutoRepository;

    @Override
    public void deleteCompareAuto(Long idAuto, Long idUser) {
        compareAutoRepository.deleteByIdAutoAndIdUser(idAuto, idUser);
    }
}
