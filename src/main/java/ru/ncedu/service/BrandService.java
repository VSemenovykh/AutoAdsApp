package ru.ncedu.service;

import ru.ncedu.entity.Brand;
import ru.ncedu.entity.Motor;

import java.util.List;

public interface BrandService {
    List<Brand> getAllBrand();
    List<Brand> findAll(int pageNumber, int rowPerPage);
    Brand save(Brand brand);
    Brand findById(Long id);
    void update(Brand brand);
    Brand findBrandByIdBrand(Long idAuto);
    void delete(Long idAuto);
}
