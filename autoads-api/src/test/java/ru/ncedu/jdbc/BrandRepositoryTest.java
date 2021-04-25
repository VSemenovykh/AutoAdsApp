package ru.ncedu.jdbc;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import static org.assertj.core.api.Assertions.*;
import ru.ncedu.entity.Brand;
import ru.ncedu.repository.BrandRepository;

//???????
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class BrandRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BrandRepository brandRepository;

    @Test
    public void testBrand(){
        this.entityManager.persist(new Brand(1L,"AUDI","A3","2016"));
        Brand brand = this.brandRepository.getOne(1L);
        assertThat(brand.getNameBrand()).isEqualTo("AUDI");
        assertThat(brand.getNameModel()).isEqualTo("A3");
        assertThat(brand.getYear()).isEqualTo("2016");
    }
}
