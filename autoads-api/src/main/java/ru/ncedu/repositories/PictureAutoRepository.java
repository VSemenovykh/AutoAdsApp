package ru.ncedu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ncedu.entity.PictureAuto;

@Repository
public interface PictureAutoRepository extends JpaRepository<PictureAuto, Long> {

    PictureAuto findByNameImage(String nameImage);

    PictureAuto findImageAutoById(Long idAuto);
}
