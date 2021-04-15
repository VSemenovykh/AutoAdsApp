package ru.ncedu.service;

import ru.ncedu.model.DataAuto;

public interface CreateAutoService {

    DataAuto saveAuto(DataAuto dataAuto, Long idImage);
}
