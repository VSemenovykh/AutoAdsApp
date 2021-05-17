package ru.ncedu.services;

import org.springframework.http.ResponseEntity;
import ru.ncedu.entity.Brand;

import java.util.List;

public interface BrandService {

    Brand findById(Long id);

    ResponseEntity<List<Brand>> findAllBrand();
}
