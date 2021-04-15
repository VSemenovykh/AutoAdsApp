package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ncedu.repository.CompareAutoRepository;
import ru.ncedu.service.DeleteListCompareAutoService;

@Service
@RequiredArgsConstructor
public class DeleteListCompareAutoServiceImp implements DeleteListCompareAutoService {

    private final CompareAutoRepository compareAutoRepository;

    @Override
    public void clearListCompareAuto() {
        compareAutoRepository.deleteAll();
    }
}
