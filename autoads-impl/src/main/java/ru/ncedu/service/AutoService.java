package ru.ncedu.service;

import ru.ncedu.model.AutoJoin;
import java.util.List;

public interface AutoService {

    AutoJoin findById(Long id);

    /*search by different criteria */
    List<AutoJoin> searchAuto(
                              String nameBrand,
                              String nameModel,
                              String startYear,
                              String endYear,
                              String color,
                              Double startPrice,
                              Double endPrice,
                              String motorType,
                              Double startVolume,
                              Double endVolume,
                              String drive,
                              String transmission,
                              String bodyStyle
                            );

}
