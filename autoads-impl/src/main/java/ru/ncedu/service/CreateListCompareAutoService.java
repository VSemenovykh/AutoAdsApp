package ru.ncedu.service;
import ru.ncedu.entity.CompareAuto;
import ru.ncedu.model.AutoJoin;

public interface CreateListCompareAutoService {

   CompareAuto addAutoToListCompare(AutoJoin autoJoin);
}
