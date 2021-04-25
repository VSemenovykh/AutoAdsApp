package ru.ncedu.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ncedu.entity.Brand;
import ru.ncedu.repository.BrandRepository;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CreateBrandServiceImpTest {

    @Autowired
    private BrandRepository brandRepository;

    @Test
    public void testCreateBrand(){
        Brand testBrand = new Brand();
        testBrand.setId(1L);
        testBrand.setNameBrand("AUDI");
        testBrand.setNameModel("A3");
        testBrand.setYear("2016");

        brandRepository.save(testBrand);

        Brand brand = brandRepository.getOne(1l);
        assertNotNull(brand);
        assertEquals(testBrand.getNameBrand(), brand.getNameBrand());
        assertEquals(testBrand.getNameModel(), brand.getNameModel());
        assertEquals(testBrand.getYear(), brand.getYear());
    }
}
