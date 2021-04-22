package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ncedu.repository.CompareAutoRepository;
import ru.ncedu.service.DeleteListCompareAutoService;
import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DeleteListCompareAutoServiceImp implements DeleteListCompareAutoService {

    private final CompareAutoRepository compareAutoRepository;

    @Override
    public void clearListCompareAuto(Long idUser) {
        compareAutoRepository.deleteAllByIdUser(idUser);
    }
}
