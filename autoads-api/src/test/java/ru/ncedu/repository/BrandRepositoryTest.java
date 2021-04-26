package ru.ncedu.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;
import ru.ncedu.entity.Brand;
import ru.ncedu.implement.BrandRepositoryTestImp;

import java.util.List;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class BrandRepositoryTest {

    //    @Mock
    private BrandRepositoryTestImp brandRepositoryTest;

    //?????
//    @BeforeEach
//    public void setUp() {
//        brandRepositoryTest = new BrandRepositoryTestImp();
//    }

    @Test
    public void testFindBrandById() {
        brandRepositoryTest = new BrandRepositoryTestImp();
        Brand testBrand = brandRepositoryTest.getBrandById(1L);
        assertThat(testBrand).isEqualTo(new Brand(1L, "AUDI", "A3", "2016"));
    }

    @Test
    public void testSaveBrand() {
        brandRepositoryTest = new BrandRepositoryTestImp();
        brandRepositoryTest.saveBrand(new Brand(8L, "HONDA", "CROSSTOUR", "2016"));
        Brand testBrand = brandRepositoryTest.getBrandById(8L);
        assertThat(testBrand).isEqualTo(new Brand(8L, "HONDA", "CROSSTOUR", "2016"));
    }

    @Test
    public void testFindAll() {
        brandRepositoryTest = new BrandRepositoryTestImp();
        List<Brand> brandList = brandRepositoryTest.findAll();
        assertThat(brandList).isNotNull();
        log.info("Added brand successfully");
    }

    @Test
    public void testDelete() {
        brandRepositoryTest = new BrandRepositoryTestImp();
        testSaveBrand();
        brandRepositoryTest.delete(8L);
        log.info("Deleted brand from list");
    }
}
