package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.repositories.UserRepository;
import ru.ncedu.services.DeleteListCompareAutoAdsService;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/all")
public class DeleteListCompareAutoAdsController {

    private final DeleteListCompareAutoAdsService deleteListCompareAutoAdsService;
    private final UserRepository userRepository;

    @PostAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/search/list-compare-auto/clear")
    public HttpStatus clearListCompareAuto(@RequestParam Long idUser) {
        if (checkId(idUser)) {
            deleteListCompareAutoAdsService.clearListCompareAuto(idUser);

            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    public boolean checkId(Long idUser) {
        return userRepository.existsById(idUser);
    }
}
