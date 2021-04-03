package ru.ncedu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ncedu.entity.Auto;
import ru.ncedu.model.AutoJoin;
import java.util.List;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Long> {

    Page<Auto> findAll(Pageable pageable);

    String querySearch = "SELECT new ru.ncedu.model.AutoJoin( a.id," +
                                                             " ia.id,"+
                                                             " ia.raster,"+
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
                                 " WHERE " +
                                 " (:nameBrand is null or b.nameBrand = :nameBrand) " +
                                 " and (:nameModel is null or b.nameModel = :nameModel) " +
                                 " and ( (:startYear is null and :endYear is null) " +
                                         "or (b.year between :startYear and :endYear) " +
                                         "or (:endYear is null and b.year >= :startYear) " +
                                         "or (:startYear is null and b.year <= :endYear) ) " +
                                 " and (:color is null or a.color = :color) " +
                                 " and ( (:startPrice is null and :endPrice is null) " +
                                         "or ( a.price between :startPrice and :endPrice) " +
                                         "or (:endPrice is null and a.price >= :startPrice) " +
                                         "or (:startPrice is null and a.price <= :endPrice) ) " +
                                 " and (:motorType is null or m.motorType = :motorType) " +
                                 " and ( (:startVolume is null and :endVolume is null) " +
                                         "or (m.volume  between :startVolume and :endVolume) " +
                                         "or (:endVolume is null and  m.volume  >= :startVolume) " +
                                         "or (:startVolume is null and  m.volume  <= :endVolume) ) " +
                                 " and (:driveType is null or a.driveType = :driveType) " +
                                 " and (:transmissionType is null or a.transmissionType = :transmissionType) " +
                                 " and (:bodyStyleType is null or a.bodyStyleType = :bodyStyleType) ";

    @Query(value = querySearch, nativeQuery = false)
    List<AutoJoin> searchAuto( @Param("nameBrand") String nameBrand,
                               @Param("nameModel") String nameModel,
                               @Param("startYear") String startYear,
                               @Param("endYear") String endYear,
                               @Param("color") String color,
                               @Param("startPrice") Double startPrice,
                               @Param("endPrice") Double endPrice,
                               @Param("motorType") String motorType,
                               @Param("startVolume") Double startVolume,
                               @Param("endVolume") Double endVolume,
                               @Param("driveType") String driveType,
                               @Param("transmissionType") String transmissionType,
                               @Param("bodyStyleType") String bodyStyleType );

    @Query(value = querySearch, nativeQuery = false)
    Page<AutoJoin>  searchAutoPage( @Param("nameBrand") String nameBrand,
                               @Param("nameModel") String nameModel,
                               @Param("startYear") String startYear,
                               @Param("endYear") String endYear,
                               @Param("color") String color,
                               @Param("startPrice") Double startPrice,
                               @Param("endPrice") Double endPrice,
                               @Param("motorType") String motorType,
                               @Param("startVolume") Double startVolume,
                               @Param("endVolume") Double endVolume,
                               @Param("driveType") String driveType,
                               @Param("transmissionType") String transmissionType,
                               @Param("bodyStyleType") String bodyStyleType,
                               Pageable pageable);
}
