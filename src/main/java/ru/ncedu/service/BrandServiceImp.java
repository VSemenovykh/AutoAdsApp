package ru.ncedu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncedu.entity.Auto;
import ru.ncedu.entity.Brand;
import ru.ncedu.entity.Motor;
import ru.ncedu.repository.BrandRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BrandServiceImp implements BrandService{
    @Autowired
    BrandRepository brandRepository;

    @Override
    public List<Brand> getAllBrand(){
       return brandRepository.findAll();
    }

    @Override
    public List<Brand> findAll(int pageNumber, int rowPerPage) {
        List<Brand> contacts = new ArrayList<>();
        Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, rowPerPage,
                Sort.by("id").ascending());
        brandRepository.findAll(sortedByIdAsc).forEach(contacts::add);
        return contacts;
    }

    @Override
    public Brand save(Brand brand){
        return brandRepository.save(brand);
    }

    @Override
    public Brand findById(Long id){
        Brand brand = brandRepository.findById(id).orElse(null);
        return brand;
    }

    @Override
    public Brand findBrandByIdBrand(Long idAuto){
        Brand brand = brandRepository.findBrandByIdAuto(idAuto);
        return brand;
    }

    @Override
    public void update(Brand brand){
        brandRepository.save(brand);
    }

    @Override
    public void delete(Long idAuto){
        brandRepository.deleteByIdAuto(idAuto);
    }
}
