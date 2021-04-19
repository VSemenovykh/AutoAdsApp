package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.*;
import ru.ncedu.model.DataAuto;
import ru.ncedu.repository.CompareAutoRepository;
import ru.ncedu.service.*;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateListCompareAutoServiceImp implements CreateListCompareAutoService {

    private final CompareAutoRepository compareAutoRepository;

    @Override
    public CompareAuto addAutoToListCompare(DataAuto dataAuto) {
        log.info("CreateListCompareAutoServiceImp -> addAutoToListCompare()");
        log.info("CreateListCompareAutoServiceImp -> DataAuto -> isNull:" + isNull(dataAuto));
        CompareAuto compareAuto = compareAutoRepository.findByIdAuto(dataAuto.getId());
        log.info("CreateListCompareAutoServiceImp -> CompareAuto -> isNull:" + isNull(compareAuto));
        return (compareAuto != null) ? compareAuto : compareAutoRepository.save(new CompareAuto(null, dataAuto.getId()));
//        if (compareAuto != null) {
//            return compareAuto;
//        } else {
//            return compareAutoRepository.save(new CompareAuto(null, dataAuto.getId()));
//        }
    }
}
