package ru.ncedu.service;

import ru.ncedu.entity.Brand;
import java.util.List;

public interface BrandService {

    Brand save(Brand brand);

    void update(Brand brand);

    void delete(Long idBrand);

    List<Brand> getAllBrand();

    List<Brand> findAll();

    Brand findById(Long id);

    List<Brand> findByNameBrand(String brand);

    List<Brand> findByNameModel(String model);

    List<Brand> findByYear(String year);

    List<Brand> searchBrand(String brand, String model, String year);
}
