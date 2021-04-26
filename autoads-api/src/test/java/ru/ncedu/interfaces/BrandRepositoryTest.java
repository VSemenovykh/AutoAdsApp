package ru.ncedu.interfaces;

import ru.ncedu.entity.Brand;

import java.util.List;

public interface BrandRepositoryTest {

    Brand getBrandById(Long id);

    void saveBrand(Brand brand);

    List<Brand> findAll();

    void delete(Long id);
}
