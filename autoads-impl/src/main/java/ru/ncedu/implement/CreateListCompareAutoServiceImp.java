package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.*;
import ru.ncedu.model.DataAuto;
import ru.ncedu.repository.CompareAutoRepository;
import ru.ncedu.service.*;

@Service
@RequiredArgsConstructor
public class CreateListCompareAutoServiceImp implements CreateListCompareAutoService {

    private final CompareAutoRepository compareAutoRepository;

    @Override
    public CompareAuto addAutoToListCompare(DataAuto dataAuto) {
        CompareAuto compareAuto = compareAutoRepository.findByIdAuto(dataAuto.getId());
        if (compareAuto != null) {
            return compareAuto;
        } else {
            return compareAutoRepository.save(new CompareAuto(null, dataAuto.getId()));
        }
    }
}
