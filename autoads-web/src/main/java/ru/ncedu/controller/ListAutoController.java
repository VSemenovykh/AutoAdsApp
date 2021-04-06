package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.service.ListAutoService;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class ListAutoController {

    private final ListAutoService listAutoService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllAutoPage(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "3") int size) {
        return listAutoService.findAllAutoJoinPage(page,size);
    }
}
