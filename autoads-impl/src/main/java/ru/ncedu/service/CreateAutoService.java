package ru.ncedu.service;

import ru.ncedu.model.AutoJoin;

public interface CreateAutoService {

    AutoJoin saveAuto(AutoJoin autoJoin, Long idImage);
}
