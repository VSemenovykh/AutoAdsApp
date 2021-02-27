package ru.ncedu.service;

import ru.ncedu.entity.Brand;

import java.util.List;

public interface BrandService {

    List<Brand> getAllBrand();

    List<Brand> findAll(int pageNumber, int rowPerPage);

    public List<Brand> findAll();

    Brand save(Brand brand);

    Brand findById(Long id);

    void update(Brand brand);

    Brand findBrandByIdBrand(Long idBrand);

    void delete(Long idBrand);
}
