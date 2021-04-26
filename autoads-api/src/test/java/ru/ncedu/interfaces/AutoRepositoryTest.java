package ru.ncedu.interfaces;

import ru.ncedu.entity.Auto;

import java.util.List;

public interface AutoRepositoryTest {

    Auto getAutoById(Long id);

    void saveAuto(Auto auto);

    List<Auto> findAll();

    void delete(Long id);
}
