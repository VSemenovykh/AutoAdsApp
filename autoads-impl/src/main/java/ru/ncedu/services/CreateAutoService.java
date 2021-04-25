package ru.ncedu.services;

import ru.ncedu.model.DataAuto;

public interface CreateAutoService {

    DataAuto saveAuto(DataAuto dataAuto, Long idImage);
}
