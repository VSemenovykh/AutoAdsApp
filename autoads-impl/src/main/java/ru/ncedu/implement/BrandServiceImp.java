package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.Brand;
import ru.ncedu.repository.BrandRepository;
import ru.ncedu.service.BrandService;

@Service
@RequiredArgsConstructor
public class BrandServiceImp implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public Brand findById(Long id) {
        return brandRepository.findById(id).orElse(null);
    }
}
