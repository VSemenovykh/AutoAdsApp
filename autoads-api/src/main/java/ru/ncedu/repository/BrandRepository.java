package ru.ncedu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ncedu.entity.Brand;
import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    void deleteById(Long idBrand);

    List<Brand> findAll();
}
