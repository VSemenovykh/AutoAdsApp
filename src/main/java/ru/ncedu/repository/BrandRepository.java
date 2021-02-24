package ru.ncedu.repository;	

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.ncedu.entity.Brand;
import ru.ncedu.entity.Motor;

import java.util.List;

@Repository	
public interface BrandRepository extends PagingAndSortingRepository<Brand, Long>, JpaSpecificationExecutor<Brand> {
    List<Brand> findAll();
    Brand findBrandByIdAuto(Long idAuto);
    void deleteByIdAuto(Long idAuto);
}