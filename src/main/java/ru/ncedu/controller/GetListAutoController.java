package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ncedu.model.AutoJoin;
import ru.ncedu.service.AutoService;
import ru.ncedu.service.ListAutoServiceImp;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class GetListAutoController {

    private final AutoService autoService;
    private final ListAutoServiceImp listAutoServiceImp;

    @GetMapping
    public List<AutoJoin> getAllAuto() {
        List<AutoJoin> autoJoinList = listAutoServiceImp.getListAuto();
        return autoJoinList;
    }
}