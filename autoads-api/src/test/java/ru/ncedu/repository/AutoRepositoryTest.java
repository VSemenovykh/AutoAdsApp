package ru.ncedu.repository;

import ru.ncedu.entity.Auto;

public interface AutoRepositoryTest {

    Auto getAutoById(Long id);

    void saveAuto(Auto auto);
}
