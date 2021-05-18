package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.repositories.UserRepository;
import ru.ncedu.services.ListCompareAutoAdsService;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/all")
public class ListCompareAutoAdsController {

    private final ListCompareAutoAdsService listCompareAutoAdsService;
    private final UserRepository userRepository;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/search/list-compare-auto")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getAllAutoComparePage(@RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "3") int size,
                                                                     @RequestParam("idUser") Long idUser) {
        if (checkId(idUser)) {
            return listCompareAutoAdsService.findAllAutoAdsForCompare(page, size, idUser);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public boolean checkId(Long id) {
        return userRepository.existsById(id);
    }
}
