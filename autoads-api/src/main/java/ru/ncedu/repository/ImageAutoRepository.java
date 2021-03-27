package ru.ncedu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ncedu.entity.ImageAuto;

@Repository
public interface ImageAutoRepository extends JpaRepository<ImageAuto, Long> {

    ImageAuto findByNameImage(String nameImage);

    ImageAuto findImageAutoById(Long idAuto);
}
