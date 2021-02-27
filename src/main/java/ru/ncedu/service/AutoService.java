package ru.ncedu.service;

import ru.ncedu.entity.Auto;

import java.util.List;

public interface AutoService {

    List<Auto> getAllAuto();

    List<Auto> findAll(int pageNumber, int rowPerPage);

    List<Auto> findAll();

    Long count();

    Auto save(Auto auto);

    Auto findById(Long id);

    void update(Auto auto);

    void delete(Long id);
}