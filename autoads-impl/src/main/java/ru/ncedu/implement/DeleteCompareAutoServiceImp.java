package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncedu.repository.CompareAutoRepository;
import ru.ncedu.service.DeleteCompareAutoService;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteCompareAutoServiceImp implements DeleteCompareAutoService {

    private final CompareAutoRepository compareAutoRepository;

    @Override
    public void deleteCompareAuto(Long idAuto) {
        compareAutoRepository.deleteByIdAuto(idAuto);
    }
}
