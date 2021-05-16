package ru.ncedu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ncedu.entity.ChangeHistoryAutoAds;

@Repository
public interface ChangeHistoryAutoAdsRepository extends JpaRepository<ChangeHistoryAutoAds, Long> {

    ChangeHistoryAutoAds findByIdAuto(Long idAuto);
}
