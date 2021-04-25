package ru.ncedu.services;

import ru.ncedu.entity.Contact;

public interface ContactService {

    Contact findById(Long id);
}
