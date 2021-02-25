package ru.ncedu.service;

import ru.ncedu.entity.Brand;
import ru.ncedu.entity.Motor;
import ru.ncedu.exception.BadResourceException;
import ru.ncedu.exception.ResourceAlreadyExistsException;
import ru.ncedu.exception.ResourceNotFoundException;

import java.util.List;

public interface BrandService {

    List<Brand> getAllBrand();

    List<Brand> findAll(int pageNumber, int rowPerPage);

    Brand save(Brand brand) throws BadResourceException, ResourceAlreadyExistsException;

    Brand findById(Long id) throws ResourceNotFoundException;

    void update(Brand brand) throws BadResourceException, ResourceNotFoundException;

    Brand findBrandByIdBrand(Long idAuto);

    void delete(Long id) throws ResourceNotFoundException;
}
