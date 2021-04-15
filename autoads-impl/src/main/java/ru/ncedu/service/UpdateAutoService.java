package ru.ncedu.service;

import ru.ncedu.model.DataAuto;

public interface UpdateAutoService {

    void updateAuto(DataAuto auto, Long autoId, Long idImage);
}
