package ru.ncedu.ControllerTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.ncedu.entity.Contact;
import ru.ncedu.repository.ContactRepository;
import static org.assertj.core.api.Assertions.assertThat;

//??????????
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ContactRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ContactRepository contactRepository;

    @Test
    public void testContact() {
        Contact testContact = new Contact();
        testContact.setId(1L);
        testContact.setEmail("audi@gmail.com");
        testContact.setPhone("+7(111)-111-11-11");

        this.entityManager.persist(testContact);
        Contact contact = this.contactRepository.getOne(1L);
        assertThat(contact.getId()).isEqualTo(1L);
        assertThat(contact.getEmail()).isEqualTo("audi@gmail.com");
        assertThat(contact.getPhone()).isEqualTo("+7(111)-111-11-11");
    }
}
