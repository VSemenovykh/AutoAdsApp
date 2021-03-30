package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncedu.repository.AutoRepository;
import ru.ncedu.service.DeleteAutoService;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteAutoServiceImp implements DeleteAutoService {

    private final AutoRepository autorepository;

    public void deleteAuto(Long id) {
        autorepository.findById(id)
                .ifPresent(auto -> {
                    autorepository.deleteById(id);
                });
    }
}
