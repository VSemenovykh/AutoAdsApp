package ru.ncedu.service;

import ru.ncedu.model.AutoJoin;

public interface UpdateAutoService {

    void updateAuto(AutoJoin auto, Long autoId, Long idImage);
}
