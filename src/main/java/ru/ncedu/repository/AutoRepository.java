package ru.ncedu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ncedu.entity.Auto;
import ru.ncedu.model.AutoJoin;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AutoRepository extends JpaRepository<Auto, Long> {

    /*search by different criteria */
//    @Query(value = " SELECT a " +
//                    " FROM Auto a " +
//                    " where" +
//                    " (:idBrand is null or a.idBrand = :idBrand ) " +
//                    " and (:idMotor is null or a.idMotor = :idMotor )" +
//                    " and (:color is null or a.color = :color ) " +
//                    " and (:price is null or a.price >= :price) " +
//                    " and (:driveType is null or a.driveType = :driveType)" +
//                    " and (:transmissionType is null or a.transmissionType = :transmissionType) " +
//                    " and (: bodyStyleType is null or a.bodyStyleType = :bodyStyleType)"
//                    , nativeQuery = false)

    final String querySearch = "SELECT new ru.ncedu.model.AutoJoin( a.id," +
                                                                  " b.nameBrand, " +
                                                                  " b.nameModel, " +
                                                                  " b.year, " +
                                                                  " a.color," +
                                                                  " a.price, " +
                                                                  " m.motorType," +
                                                                  " m.volume," +
                                                                  " a.driveType, " +
                                                                  " a.transmissionType, " +
                                                                  " a.bodyStyleType) " +
                                 " FROM Auto a " +
                                 " JOIN Brand b on b.id = a.idBrand " +
                                 " JOIN Motor m on m.id = a.idMotor " +
                                 " WHERE " +
                                 " ( :nameBrand is null or b.nameBrand = :nameBrand ) "
        //                       + " and ( :nameModel is null or b.nameModel = :nameModel ) "
        //                       + " and ( :startYear is null or :endYear is null or ( b.year between :startYear and :endYear ) ) "
        //                       + " and ( :color is null or a.color = :color ) "
        //                      + " and ( :price is null or a.price >= :price ) "
        //                        + " and ( :motorType is null or m.motorType = :motorType ) "
                                  + " and ( :volume is null or m.volume = :volume ) "
            //                    + " and ( :driveType is null or a.driveType = :driveType ) "
        //                    + " and ( :transmissionType is null or a.transmissionType = :transmissionType ) "
                            + " and ( :bodyStyleType is null or a.bodyStyleType = :bodyStyleType ) ";
                         ;
    @Query(value = querySearch, nativeQuery = false)
    List<AutoJoin> searchAuto(
                               @Param("nameBrand") String nameBrand
//                              ,@Param("nameModel") String nameModel
//                              ,@Param("startYear") String startYear
//                              ,@Param("endYear") String endYear
//                              ,@Param("color") String color
//                              ,@Param("price") double price
//                              ,@Param("motorType") String motorType
                              ,@Param("volume") double volume
//                              ,@Param("driveType") String driveType
//                              ,@Param("transmissionType") String transmissionType
                              ,@Param("bodyStyleType") String bodyStyleType
                              );
}
