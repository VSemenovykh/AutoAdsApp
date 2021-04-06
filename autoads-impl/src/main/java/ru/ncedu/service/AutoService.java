package ru.ncedu.service;

import ru.ncedu.entity.Auto;
import ru.ncedu.model.AutoJoin;

public interface AutoService {

    Auto findById(Long id);

    AutoJoin findAutoJoinById(Long id);
}
