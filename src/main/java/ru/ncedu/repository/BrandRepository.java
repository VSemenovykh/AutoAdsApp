package ru.ncedu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ncedu.entity.Brand;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    void deleteById(Long idBrand);

    List<Brand> findAll();

    /*search by different criteria */
    final String querySearchBrand = " SELECT b " +
                                    " FROM Brand b " +
                                    " where " +
                                    " ( :nameBrand is null or b.nameBrand = :nameBrand ) " +
                                    " and ( :nameModel is null or b.nameModel = :nameModel ) " +
                                    " and ( :year is null or b.year = :year ) ";
    @Query(value = querySearchBrand, nativeQuery = false )
    List<Brand> search(@Param("nameBrand") String nameBrand, @Param("nameModel") String nameModel, @Param("year") String year);

}
