package ru.ncedu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ncedu.entity.CompareAuto;

@Repository
public interface CompareAutoRepository extends JpaRepository<CompareAuto, Long> {

    Page<CompareAuto> findAllByIdUser(Pageable pageable, Long idUser);

    CompareAuto findCompareAutoByIdUserAndIdAuto(Long idUser, Long idAuto);

    CompareAuto findByIdUserAndIdAuto(Long idUser, Long idAuto);

    void deleteByIdAutoAndIdUser(Long idAuto, Long idUser);

    void deleteByIdAuto(Long idAuto);

    void deleteAllByIdUser(Long idUser);
}
