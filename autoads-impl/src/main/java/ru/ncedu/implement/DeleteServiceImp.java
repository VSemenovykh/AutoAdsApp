package  ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncedu.entity.Auto;
import ru.ncedu.repository.AutoRepository;
import ru.ncedu.service.DeleteService;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteServiceImp implements DeleteService {

    private final AutoRepository autorepository;

    public void deleteAuto(Long id) {
        Auto auto = autorepository.findById(id).orElse(null);
        assert auto != null;

        autorepository.deleteById(id);
    }
}
