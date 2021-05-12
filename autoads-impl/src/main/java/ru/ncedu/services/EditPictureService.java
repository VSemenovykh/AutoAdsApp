package ru.ncedu.services;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface EditPictureService {

    Long editPictureAuto(MultipartFile file, Long id) throws IOException;
}
