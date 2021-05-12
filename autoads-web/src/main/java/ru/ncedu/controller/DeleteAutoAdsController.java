package ru.ncedu.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import ru.ncedu.repositories.AutoRepository;
import ru.ncedu.services.DeleteAutoAdsService;
import javax.validation.ValidationException;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/admin")
public class DeleteAutoAdsController {

    private final DeleteAutoAdsService deleteAutoAdsService;
    private final AutoRepository autoRepository;

    @PostAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public HttpStatus deleteAuto(@PathVariable("id") Long autoId) {
        if (checkId(autoId)) {
            deleteAutoAdsService.deleteAuto(autoId);

            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    public boolean checkId(Long id) {
        return autoRepository.existsById(id);
    }
}
