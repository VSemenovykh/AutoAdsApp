package ru.ncedu.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ncedu.entity.Brand;
import ru.ncedu.repository.BrandRepository;

//??????
@RunWith(SpringRunner.class)
@DataJpaTest
public class DeleteBrandServiceImpTest {

    @Autowired
    private BrandRepository brandRepository;

    @Test
    public void testDeleteBrandById(){
        Brand testBrand = new Brand();
        testBrand.setId(1L);
        testBrand.setNameBrand("AUDI");
        testBrand.setNameModel("A3");
        testBrand.setYear("2016");
        brandRepository.save(testBrand);
        brandRepository.deleteById(1L);
    }
}
