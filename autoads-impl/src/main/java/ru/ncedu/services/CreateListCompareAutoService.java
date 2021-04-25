package ru.ncedu.services;

import ru.ncedu.entity.CompareAuto;
import ru.ncedu.model.DataAuto;

public interface CreateListCompareAutoService {

    CompareAuto addAutoToListCompare(DataAuto dataAuto, Long idUser);
}
