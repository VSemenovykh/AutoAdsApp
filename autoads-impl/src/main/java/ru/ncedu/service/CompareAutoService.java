package ru.ncedu.service;

import ru.ncedu.entity.CompareAuto;

public interface CompareAutoService {

    CompareAuto findCompareAutoByIdAuto(Long idAuto, Long idUser);
}
