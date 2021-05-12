package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.repositories.AutoRepository;
import ru.ncedu.repositories.UserRepository;
import ru.ncedu.services.DeleteCompareAutoAdsService;
import javax.validation.ValidationException;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/all")
public class DeleteCompareAutoAdsController {

    private final DeleteCompareAutoAdsService deleteCompareAutoAdsService;
    private final AutoRepository autoRepository;
    private final UserRepository userRepository;

    @PostAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/search/list-compare-auto/{id}")
    public HttpStatus deleteCompareAuto(@PathVariable("id") Long autoId,
                                        @RequestParam("idUser") Long idUser) {
        if (checkId(autoId, idUser)) {
            deleteCompareAutoAdsService.deleteCompareAuto(autoId, idUser);

            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    public boolean checkId(Long autoId, Long idUser) {
        return (autoRepository.existsById(autoId) && userRepository.existsById(idUser));
    }
}
