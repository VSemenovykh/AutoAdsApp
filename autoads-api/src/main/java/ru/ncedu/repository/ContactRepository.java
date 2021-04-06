package ru.ncedu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ncedu.entity.Contact;

import java.util.List;

@Repository
public interface ContactRepository  extends JpaRepository<Contact, Long> {

    Contact findContactById(Long id);

    List<Contact> findAll();
}
