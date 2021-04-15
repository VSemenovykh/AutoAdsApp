package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.*;
import ru.ncedu.model.AutoJoin;
import ru.ncedu.repository.CompareAutoRepository;
import ru.ncedu.service.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateListCompareAutoServiceImp implements CreateListCompareAutoService {

    private final CompareAutoRepository compareAutoRepository;

    @Override
    public CompareAuto addAutoToListCompare(AutoJoin autoJoin) {
        CompareAuto compareAuto = compareAutoRepository.findById(autoJoin.getId()).orElse(null);
        if (compareAuto != null) {
            return compareAuto;
        } else {
            return compareAutoRepository.save(new CompareAuto(null, autoJoin.getId()));
        }
    }
}
