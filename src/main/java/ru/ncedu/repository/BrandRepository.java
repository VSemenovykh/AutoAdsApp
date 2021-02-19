package ru.ncedu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ncedu.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long>
{

}
