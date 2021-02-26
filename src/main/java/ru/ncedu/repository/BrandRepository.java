package ru.ncedu.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.ncedu.entity.Brand;

import java.util.List;

@Repository
public interface BrandRepository extends PagingAndSortingRepository<Brand, Long> {

    List<Brand> findAll();

    Brand findBrandById(Long idBrand);

    void deleteById(Long idBrand);
}