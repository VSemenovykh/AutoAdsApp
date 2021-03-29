package ru.ncedu.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UpdatePictureService {

    Long updatePictureAuto(MultipartFile file, Long id) throws IOException;
}
