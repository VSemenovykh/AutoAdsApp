package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.CompareAuto;
import ru.ncedu.repository.CompareAutoRepository;
import ru.ncedu.service.CompareAutoService;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompareAutoServiceImp implements CompareAutoService {

    private final CompareAutoRepository compareAutoRepository;

    @Override
    public CompareAuto findCompareAutoByIdAuto(Long idAuto, Long idUser) {
        CompareAuto compareAuto = compareAutoRepository.findCompareAutoByIdUserAndIdAuto(idUser, idAuto);
        return (compareAuto != null) ? compareAuto : null;
    }
}
