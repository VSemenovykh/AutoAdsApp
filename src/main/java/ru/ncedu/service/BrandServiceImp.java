package ru.ncedu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ncedu.entity.Brand;
import ru.ncedu.exception.BadResourceException;
import ru.ncedu.exception.ResourceAlreadyExistsException;
import ru.ncedu.exception.ResourceNotFoundException;
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

    private boolean existsById(Long id) {
        return brandRepository.existsById(id);
    }

    @Override
    public List<Brand> findAll(int pageNumber, int rowPerPage) {

        List<Brand> contacts = new ArrayList<>();

        Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, rowPerPage, Sort.by("id").ascending());

        brandRepository.findAll(sortedByIdAsc).forEach(contacts::add);

        return contacts;
    }

    @Override
    public Brand save(Brand brand) throws BadResourceException, ResourceAlreadyExistsException {

        if (!StringUtils.isEmpty(brand.getNameBrand())) {
            if (brand.getId() != null && existsById(brand.getId())) {

                throw new ResourceAlreadyExistsException("Brand with id: " + brand.getId() +
                        " already exists");

            }
            return brandRepository.save(brand);
        }else{
            BadResourceException exc = new BadResourceException("Failed to save brand");
            exc.addErrorMessage("Brand is null or empty");
            throw exc;
        }

    }

    @Override
    public Brand findById(Long id) throws ResourceNotFoundException {

        Brand brand = brandRepository.findById(id).orElse(null);

        if (brand==null) {

            throw new ResourceNotFoundException("Cannot find brand with id: " + id);

        }else{

            return brand;
        }
    }

    @Override
    public Brand findBrandByIdBrand(Long idAuto){

        Brand brand = brandRepository.findBrandById(idAuto);

        return brand;
    }

    @Override
    public void update(Brand brand) throws BadResourceException, ResourceNotFoundException{

        if (!StringUtils.isEmpty(brand.getNameBrand())) {
            if (!existsById(brand.getId())) {

                throw new ResourceNotFoundException("Cannot find brand with id: " + brand.getId());
            }
            brandRepository.save(brand);

        }else{
            BadResourceException exc = new BadResourceException("Failed to save brand");
            exc.addErrorMessage("Brand is null or empty");
            throw exc;
        }
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {

        if (!existsById(id)) {
            throw new ResourceNotFoundException("Cannot find brand with id: " + id);
        }
        else {
            brandRepository.deleteById(id);
        }
    }
}
