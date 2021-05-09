package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ncedu.repositoryes.CompareAutoRepository;
import ru.ncedu.services.DeleteListCompareAutoAdsService;
import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DeleteListCompareAutoAdsServiceImp implements DeleteListCompareAutoAdsService {

    private final CompareAutoRepository compareAutoRepository;

    @Override
    public void clearListCompareAuto(Long idUser) {
        compareAutoRepository.deleteAllByIdUser(idUser);
    }
}
