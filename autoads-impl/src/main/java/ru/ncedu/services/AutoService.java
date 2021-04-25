package ru.ncedu.services;

import ru.ncedu.entity.Auto;
import ru.ncedu.model.DataAuto;

public interface AutoService {

    Auto findById(Long id);

    DataAuto findAutoJoinById(Long id);
}
