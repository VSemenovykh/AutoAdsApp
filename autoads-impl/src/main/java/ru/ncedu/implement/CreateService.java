package ru.ncedu.service;

import ru.ncedu.entity.Auto;
import ru.ncedu.model.AutoJoin;

public interface CreateService{

    AutoJoin saveAuto(Auto auto);
}
