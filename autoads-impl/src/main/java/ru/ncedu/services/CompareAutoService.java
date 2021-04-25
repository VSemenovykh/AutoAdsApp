package ru.ncedu.services;

import ru.ncedu.entity.CompareAuto;

public interface CompareAutoService {

    CompareAuto findCompareAutoByIdAuto(Long idAuto, Long idUser);
}
