package ru.ncedu.implement;

import ru.ncedu.entity.Contact;
import ru.ncedu.exceptions.NonExistingContactException;
import ru.ncedu.interfaces.ContactRepositoryTest;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class ContactRepositoryTestImp implements ContactRepositoryTest {

    private List<Contact> contactList;

    public ContactRepositoryTestImp() {
        contactList = new ArrayList<>();
        contactList.add(new Contact(1L, "audi@gmail.com", "+7(111)-111-11-11"));
        contactList.add(new Contact(2L, "ford@gmail.com", "+7(222)-222-22-22"));
        contactList.add(new Contact(3L, "honda@gmail.com", "+7(333)-333-33-33"));
        contactList.add(new Contact(4L, "hyundai@gmail.com", "+7(444)-444-44-44"));
        contactList.add(new Contact(5L, "bmv@gmail.com", "+7(555)-555-55-55"));
    }

    @Override
    public Contact findContactById(Long id) {
        for (Contact contact : this.contactList) {
            if (contact.getId().equals(id)) {
                return contact;
            }
        }
        throw new NonExistingContactException();
    }


    @Override
    public void saveContact(Contact contact) {
        this.contactList.add(contact);
    }

    @Override
    public List<Contact> findAll() {
        return (!this.contactList.isEmpty()) ? this.contactList : null;
    }

    @Override
    public void delete(Long id) {
        if (!isNull(findContactById(id))) {
            this.contactList.remove(findContactById(id));
        } else {
            throw new NonExistingContactException();
        }
    }
}
