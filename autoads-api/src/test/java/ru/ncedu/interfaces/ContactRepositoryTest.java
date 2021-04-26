package ru.ncedu.interfaces;

import ru.ncedu.entity.Contact;

import java.util.List;

public interface ContactRepositoryTest {

    Contact findContactById(Long id);

    void saveContact(Contact contact);

    List<Contact> findAll();

    void delete(Long id);

}
