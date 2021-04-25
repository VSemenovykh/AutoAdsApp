package ru.ncedu.services;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.ncedu.entity.Brand;
import ru.ncedu.implement.BrandServiceImp;
import ru.ncedu.repository.BrandRepository;

//??????????
public class BrandServiceImpTest {

    @InjectMocks
    BrandServiceImp brandServiceImp;

    @Mock
    BrandRepository brandRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindBrandById() {
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setNameBrand("AUDI");
        brand.setNameModel("A3");
        brand.setYear("2016");

        Mockito.when(brandRepository.getOne(1L)).thenReturn(brand);

        Brand testBrand = brandServiceImp.findById(1L);
        Assertions.assertEquals("AUDI", testBrand.getNameBrand());
        Assertions.assertEquals("A3", testBrand.getNameModel());
        Assertions.assertEquals("2016", testBrand.getYear());
    }
}
