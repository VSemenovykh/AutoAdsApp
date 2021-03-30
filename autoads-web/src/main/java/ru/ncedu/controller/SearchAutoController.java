package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.model.AutoJoin;
import ru.ncedu.model.DataAutoSearch;
import ru.ncedu.service.AutoService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/search")
public class SearchAutoController {

    private final AutoService autoService;

    @PostMapping
    public List<AutoJoin> searchAuto(@RequestBody DataAutoSearch dataAutoSearch) {
        return  autoService.searchAuto( dataAutoSearch.getNameBrand(),
                                        dataAutoSearch.getNameModel(),
                                        dataAutoSearch.getStartYear(),
                                        dataAutoSearch.getEndYear(),
                                        dataAutoSearch.getColor(),
                                        dataAutoSearch.getStartPrice(),
                                        dataAutoSearch.getEndPrice(),
                                        dataAutoSearch.getMotorType(),
                                        dataAutoSearch.getStartVolume(),
                                        dataAutoSearch.getEndVolume(),
                                        dataAutoSearch.getDriveType(),
                                        dataAutoSearch.getTransmissionType(),
                                        dataAutoSearch.getBodyStyleType() );
    }
}

