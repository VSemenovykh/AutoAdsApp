package ru.ncedu.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ncedu.entity.ChangeHistoryAutoAds;

@Repository
public interface ChangeHistoryAutoAdsRepository extends JpaRepository<ChangeHistoryAutoAds, Long> {

    Page<ChangeHistoryAutoAds> findAllByIdAuto(Pageable pageable, Long idAuto);
}
