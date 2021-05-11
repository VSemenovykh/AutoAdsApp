package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncedu.repositories.AutoRepository;
import ru.ncedu.repositories.CompareAutoRepository;
import ru.ncedu.services.DeleteAutoAdsService;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DeleteAutoAdsServiceImp implements DeleteAutoAdsService {

    private final AutoRepository autorepository;

    private final CompareAutoRepository compareAutoRepository;

    public void deleteAuto(Long id) {
        autorepository.findById(id)
                .ifPresent(auto -> {
                    compareAutoRepository.deleteByIdAuto(id);
                    autorepository.deleteById(id);
                });
    }
}
