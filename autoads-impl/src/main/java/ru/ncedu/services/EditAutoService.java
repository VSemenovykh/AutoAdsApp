package ru.ncedu.services;

import ru.ncedu.model.DataAuto;

public interface EditAutoService {

    void editAuto(DataAuto auto, Long autoId, Long idImage);
}
