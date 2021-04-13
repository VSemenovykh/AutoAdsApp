package ru.ncedu.service;

import ru.ncedu.entity.Contact;

public interface ContactService {

    Contact findById(Long id);
}
