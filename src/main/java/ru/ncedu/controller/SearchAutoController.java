package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.model.AutoJoin;
import ru.ncedu.service.AutoService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/search")
@Slf4j
public class SearchAutoController {

    private final AutoService autoService;

    @PostMapping
    public List<AutoJoin> searchAuto(@RequestBody AutoJoin car
                                     ,@RequestParam(value = "startYear", required = false) String startYear
                                     ,@RequestParam(value = "endYear", required = false) String endYear
                                     ,@RequestParam(value = "startPrice", required = false) double startPrice
                                     ,@RequestParam(value = "endPrice", required = false) double endPrice) {

        List<AutoJoin> newAutoList = autoService.searchAuto(
                                                             car.getNameBrand()
                                                            ,car.getNameModel()
                                                            ,startYear
                                                            ,endYear
                                                            ,car.getColor()
                                                            ,startPrice
                                                            ,endPrice
                                                            ,car.getMotorType()
                                                            ,car.getVolume()
                                                            ,car.getDriveType()
                                                            ,car.getTransmissionType()
                                                            ,car.getBodyStyleType()
                                                            );

        /*to check if the list is empty*/
        log.info("newAutoList: " + newAutoList);

        return newAutoList;
    }
}

