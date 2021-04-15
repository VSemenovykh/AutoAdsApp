package ru.ncedu.service;

import ru.ncedu.entity.Auto;
import ru.ncedu.model.DataAuto;

public interface AutoService {

    Auto findById(Long id);

    DataAuto findAutoJoinById(Long id);
}
