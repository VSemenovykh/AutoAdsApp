package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.entity.Brand;
import ru.ncedu.services.BrandService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/all")
public class ListBrandController {

    private final BrandService brandService;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @GetMapping("/brand")
    @ResponseBody
    public ResponseEntity<List<Brand>> getListBrand() {
        return brandService.findAllBrand();
    }
}
