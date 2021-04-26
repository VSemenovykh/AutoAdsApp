package ru.ncedu.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.ncedu.entity.Brand;
import ru.ncedu.exceptions.NonExistingBrandException;
import ru.ncedu.interfaces.BrandRepositoryTest;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Component
public class BrandRepositoryTestImp implements BrandRepositoryTest {

    private List<Brand> brandList;

    public BrandRepositoryTestImp(){
        brandList = new ArrayList<>();
        brandList.add(new Brand(1L, "AUDI", "A3", "2016"));
        brandList.add(new Brand(4L, "FORD", "FIESTA", "2016"));
        brandList.add(new Brand(7L, "HONDA", "ACCORD", "2015"));
        brandList.add(new Brand(10L, "HYUNDAI", "SOLARIS", "2017"));
        brandList.add(new Brand(13L, "BMW", "M8", "2015"));
    }
    @Override
    public Brand getBrandById(Long id) {
        for(Brand brand: brandList){
            if(brand.getId().equals(id)){
                return brand;
            }
        }
         throw  new NonExistingBrandException();
    }

    @Override
    public void saveBrand(Brand brand){
        this.brandList.add(brand);
    }

    @Override
    public List<Brand> findAll(){
        return (!this.brandList.isEmpty()) ? this.brandList : null;
    }

    @Override
    public void delete(Long id) {
        if (!isNull(getBrandById(id))) {
            this.brandList.remove(getBrandById(id));
        } else {
            throw new NonExistingBrandException();
        }
    }
}
