package ru.ncedu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ncedu.entity.CompareAuto;
import ru.ncedu.model.AutoJoin;

import java.util.List;

@Repository
public interface CompareAutoRepository extends JpaRepository<CompareAuto, Long> {

    Page<CompareAuto> findAll(Pageable pageable);

    void deleteByIdAuto(Long idAuto);

}
