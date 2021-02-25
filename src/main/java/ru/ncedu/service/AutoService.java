package ru.ncedu.service;

import org.springframework.data.jpa.repository.JpaRepository;	
import org.springframework.stereotype.Repository;	
import ru.ncedu.entity.Auto;
import java.util.List;

public interface AutoService{

    List<Auto> getAllAuto();

    List<Auto> findAll(int pageNumber, int rowPerPage);

    Long count();

    Auto save(Auto auto);

    Auto findById(Long id);

    void update(Auto auto);

    void delete(Long id);

}