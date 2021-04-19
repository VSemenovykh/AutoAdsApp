package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncedu.repository.AutoRepository;
import ru.ncedu.service.DeleteAutoService;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DeleteAutoServiceImp implements DeleteAutoService {

    private final AutoRepository autorepository;

    public void deleteAuto(Long id) {
        log.info("DeleteAutoServiceImp -> deleteAuto()");
        log.info("DeleteAutoServiceImp -> id: " + id);
        autorepository.findById(id)
                .ifPresent(auto -> {
                    autorepository.deleteById(id);
                });
    }
}
