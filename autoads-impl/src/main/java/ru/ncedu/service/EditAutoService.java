package ru.ncedu.service;

import ru.ncedu.model.DataAuto;

public interface EditAutoService {

    void editAuto(DataAuto auto, Long autoId, Long idImage);
}
