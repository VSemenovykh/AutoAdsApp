package ru.ncedu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncedu.entity.Auto;
import ru.ncedu.repository.AutoRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteServiceImp implements DeleteService  {

    private final AutoRepository autorepository;

    public void deleteAuto(Long id) {
        Auto auto = autorepository.findById(id).orElse(null);
        assert auto != null;
        auto.setIdBrand(0L);
        auto.setIdMotor(0L);

        autorepository.deleteById(id);
    }
}
