package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.Brand;
import ru.ncedu.repositories.BrandRepository;
import ru.ncedu.services.BrandService;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrandServiceImp implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public Brand findById(Long id) {
        return brandRepository.findById(id).orElse(null);
    }
}
