package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.service.ListCompareAutoService;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class ListCompareAutoController {

    private final ListCompareAutoService listCompareAutoService;

    @GetMapping(path = "/search/compare-auto")
    public ResponseEntity<Map<String, Object>> getAllAutoComparePage(@RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "3") int size) {
        return listCompareAutoService.findAllAutoComparePage(page, size);
    }
}
