package ru.ncedu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncedu.entity.Brand;
import ru.ncedu.repository.BrandRepository;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BrandServiceImp implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

    @Override
    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public void update(Brand brand) {
        brandRepository.save(brand);
    }

    @Override
    public void delete(Long idBrand) {
        brandRepository.deleteById(idBrand);
    }

    @Override
    public List<Brand> findAll() {
        List<Brand> brand = new ArrayList<>();
        brandRepository.findAll().forEach(brand::add);
        return brand;
    }

    @Override
    public Brand findById(Long id) {
        Brand brand = brandRepository.findById(id).orElse(null);
        return brand;
    }

    @Override
    public List<Brand> findByNameBrand(String brand){
        return brandRepository.findByNameBrand(brand);
    }

    @Override
    public List<Brand> findByNameModel(String model){
        return brandRepository.findByNameModel(model);
    }

    @Override
    public List<Brand> findByYear(String model){
        return brandRepository.findByYear(model);
    }

    @Override
    public  List<Brand> searchBrand(String brand, String model, String year){
        return brandRepository.search(brand, model, year);
    }
}
