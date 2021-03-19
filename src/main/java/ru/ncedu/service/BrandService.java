package ru.ncedu.service;

import ru.ncedu.entity.Brand;
import java.util.List;

public interface BrandService {

    List<Brand> getAllBrand();

    List<Brand> findAll();

    Brand findById(Long id);

    List<Brand> searchBrand(String brand, String model, String year);
}
