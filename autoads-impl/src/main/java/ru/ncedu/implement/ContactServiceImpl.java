package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.Contact;
import ru.ncedu.repositories.ContactRepository;
import ru.ncedu.services.ContactService;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    final private ContactRepository contactRepository;

    @Override
    public Contact findById(Long id) {
        return contactRepository.findById(id).orElse(null);
    }
}
