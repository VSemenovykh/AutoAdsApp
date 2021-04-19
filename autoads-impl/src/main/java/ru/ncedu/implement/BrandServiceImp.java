package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.Brand;
import ru.ncedu.repository.BrandRepository;
import ru.ncedu.service.BrandService;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrandServiceImp implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public Brand findById(Long id) {
        log.info("BrandServiceImp -> findById()");
        log.info("BrandServiceImp -> id: " + id);
        return brandRepository.findById(id).orElse(null);
    }
}
