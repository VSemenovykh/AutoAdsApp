package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.entity.CompareAuto;
import ru.ncedu.model.AutoJoin;
import ru.ncedu.service.CreateListCompareAutoService;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class CreateListCompareAutoController {

    private final CreateListCompareAutoService createListCompareAutoService;

    @PostMapping(path = "/add-compare-auto")
    public CompareAuto addAutoToCompare(@RequestBody AutoJoin autoJoin) {
        log.info("autoJoin: ", autoJoin.toString().length());
        log.info("Successfully, auto added list compare");
        return createListCompareAutoService.addAutoToListCompare(autoJoin);
    }

//    @GetMapping(path = "/compare-auto")
//    public ResponseEntity<Map<String, Object>> getAllAutoComparePage(@RequestParam(defaultValue = "0") int page,
//                                                                     @RequestParam(defaultValue = "3") int size) {
//
//        return createListCompareAutoService.findAllAutoComparePage(page, size);
//    }
}
