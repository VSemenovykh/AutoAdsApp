package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.entity.Auto;
import ru.ncedu.entity.Brand;
import ru.ncedu.entity.Motor;
import ru.ncedu.model.AutoJoin;
import ru.ncedu.service.AutoService;
import ru.ncedu.service.BrandService;
import ru.ncedu.service.MotorService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AutoController {

    private final int ROW_PER_PAGE = 5;

    private final AutoService autoService;

    private final BrandService brandService;

    private final MotorService motorService;

    @GetMapping("/auto")
    public String displayListCarsJoin(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber) {

        List<Auto> auto = autoService.findAll(pageNumber, ROW_PER_PAGE);
        List<Brand> brand = brandService.findAll(pageNumber, ROW_PER_PAGE);
        List<Motor> motors = motorService.findAll(pageNumber, ROW_PER_PAGE);
        List<AutoJoin> listAutoJoin = new ArrayList<>();

        AutoJoin autoJoin;

        String brandName;
        String modelName;
        String year;

        String motorType;
        double volume;

        for (int i = 0; i < auto.size(); i++) {

            brandName = brandService.findById(auto.get(i).getIdBrand()).getNameBrand();
            modelName = brandService.findById(auto.get(i).getIdBrand()).getNameModel();
            year = brandService.findById(auto.get(i).getIdBrand()).getYear();

            motorType = motorService.findById(auto.get(i).getIdMotor()).getMotorType();
            volume = motorService.findById(auto.get(i).getIdMotor()).getVolume();

            autoJoin = new AutoJoin(auto.get(i).getId(), brandName, modelName, year, auto.get(i).getColor()
                    , auto.get(i).getPrice(), motorType, volume, auto.get(i).getDriveType()
                    , auto.get(i).getTransmissionType(), auto.get(i).getBodyStyleType());

            listAutoJoin.add(autoJoin);
        }

        long count = autoService.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROW_PER_PAGE) < count;

        model.addAttribute("listAutoJoin", listAutoJoin);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);

        return "auto-list";
    }
}