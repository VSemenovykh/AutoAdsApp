package ru.ncedu.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ncedu.entity.Contact;
import ru.ncedu.implement.ContactRepositoryTestImp;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class ContactRepositoryTest {


    //    @Mock
    private ContactRepositoryTestImp contactRepositoryTestImp;

    //?????
//    @BeforeEach
//    public void setUp() {
//        brandRepositoryTest = new BrandRepositoryTestImp();
//    }

    @Test
    public void testFindContactById() {
        contactRepositoryTestImp = new ContactRepositoryTestImp();
        Contact testContact = contactRepositoryTestImp.findContactById(1L);
        assertThat(testContact).isEqualTo(new Contact(1L, "audi@gmail.com", "+7(111)-111-11-11"));
    }

    @Test
    public void testSaveContact() {
        contactRepositoryTestImp = new ContactRepositoryTestImp();
        contactRepositoryTestImp.saveContact(new Contact(7L, "kia@gmail.com", "+7(777)-777-77-77"));
        Contact testContact = contactRepositoryTestImp.findContactById(7L);
        assertThat(testContact).isEqualTo(new Contact(7L, "kia@gmail.com", "+7(777)-777-77-77"));
    }

    @Test
    public void testFindAll() {
        contactRepositoryTestImp = new ContactRepositoryTestImp();
        List<Contact> contactList = contactRepositoryTestImp.findAll();
        assertThat(contactList).isNotNull();
        log.info("Added contact successfully");
    }

    @Test
    public void testDelete() {
        contactRepositoryTestImp = new ContactRepositoryTestImp();
        testSaveContact();
        contactRepositoryTestImp.delete(7L);
        log.info("Deleted contact from list");
    }
}
