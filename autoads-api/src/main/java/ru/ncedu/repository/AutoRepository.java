package ru.ncedu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ncedu.entity.Auto;
import ru.ncedu.model.DataAuto;
import java.util.List;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Long> {

    Page<Auto> findAll(Pageable pageable);

    Auto getAutoById(Long id);

    String queryMultipleSearch = "SELECT new ru.ncedu.model.DataAuto( a.id," +
                                                                    " ia.id," +
                                                                    " ia.raster," +
                                                                    " c.email,"+
                                                                    " c.phone,"+
                                                                    " b.nameBrand, " +
                                                                    " b.nameModel, " +
                                                                    " b.year, " +
                                                                    " a.color," +
                                                                    " a.price, " +
                                                                    " m.motorType," +
                                                                    " m.volume," +
                                                                    " a.driveType, " +
                                                                    " a.transmissionType, " +
                                                                    " a.bodyStyleType ) " +
                                        " FROM Auto a " +
                                        " JOIN Brand b on b.id = a.idBrand " +
                                        " JOIN Motor m on m.id = a.idMotor " +
                                        " LEFT JOIN PictureAuto ia on ia.id = a.idImage " +
                                        " LEFT JOIN Contact c on c.id = a.idContact " +
                                        " WHERE " +
                                        " ((:nameBrand) is null or b.nameBrand IN (:nameBrand)) " +
                                        " and ((:nameModel) is null or b.nameModel IN (:nameModel)) " +
                                        " and ( (:startYear is null and :endYear is null) " +
                                        "or (b.year between :startYear and :endYear) " +
                                        "or (:endYear is null and b.year >= :startYear) " +
                                        "or (:startYear is null and b.year <= :endYear) ) " +
                                        " and ((:color) is null or a.color IN (:color)) " +
                                        " and ( (:startPrice is null and :endPrice is null) " +
                                        "or ( a.price between :startPrice and :endPrice) " +
                                        "or (:endPrice is null and a.price >= :startPrice) " +
                                        "or (:startPrice is null and a.price <= :endPrice) ) " +
                                        " and ((:motorType) is null or m.motorType IN (:motorType)) " +
                                        " and ( (:startVolume is null and :endVolume is null) " +
                                        "or (m.volume  between :startVolume and :endVolume) " +
                                        "or (:endVolume is null and  m.volume  >= :startVolume) " +
                                        "or (:startVolume is null and  m.volume  <= :endVolume) ) " +
                                        " and ((:driveType) is null or a.driveType IN (:driveType)) " +
                                        " and ((:transmissionType) is null or a.transmissionType IN (:transmissionType)) " +
                                        " and ((:bodyStyleType) is null or a.bodyStyleType IN (:bodyStyleType)) ";
    @Query(value = queryMultipleSearch, nativeQuery = false)
    Page<DataAuto> searchAutoPage(@Param("nameBrand") List<String> nameBrand,
                                  @Param("nameModel") List<String> nameModel,
                                  @Param("startYear") String startYear,
                                  @Param("endYear") String endYear,
                                  @Param("color") List<String> color,
                                  @Param("startPrice") Double startPrice,
                                  @Param("endPrice") Double endPrice,
                                  @Param("motorType") List<String> motorType,
                                  @Param("startVolume") Double startVolume,
                                  @Param("endVolume") Double endVolume,
                                  @Param("driveType") List<String> driveType,
                                  @Param("transmissionType") List<String> transmissionType,
                                  @Param("bodyStyleType") List<String> bodyStyleType,
                                  Pageable pageable);
}
