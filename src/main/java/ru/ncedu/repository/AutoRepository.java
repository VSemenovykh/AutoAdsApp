package ru.ncedu.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ncedu.entity.Auto;

import java.util.List;

@Repository
//@Transactional
public interface AutoRepository extends PagingAndSortingRepository<Auto, Long> {

    List<Auto> findAll();

    void deleteById(Long id);
}
