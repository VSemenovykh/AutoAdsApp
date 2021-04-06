package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ncedu.entity.Contact;
import ru.ncedu.repository.ContactRepository;
import ru.ncedu.service.ContactService;

@Service
@RequiredArgsConstructor
public class ContactServiceImp implements ContactService {

    final private ContactRepository contactRepository;

    @Override
    public Contact findById(Long id){
        return contactRepository.findById(id).orElse(null);
    }
}
