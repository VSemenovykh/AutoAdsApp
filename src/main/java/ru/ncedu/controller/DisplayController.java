package ru.ncedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
public class DisplayController {
    @Autowired
    AutoService autoService;

    @Autowired
    BrandService brandService;

    @Autowired
    MotorService motorService;

    @GetMapping("/listAutoJoin")
    public String displayListCarsJoin(Model model){
        List<Auto> auto = autoService.getAllAuto();
        List<Brand> brand = brandService.getAllBrand();
        List<Motor> motors = motorService.getAllMotor();
        List<AutoJoin> listAutoJoin = new ArrayList<>();
        AutoJoin autoJoin;
        
        for(int i = 0; i < auto.size(); i++){
            autoJoin = new AutoJoin(auto.get(i).getId(), brand.get(i).getNameBrand(), brand.get(i).getNameModel()
                                    ,brand.get(i).getYear(), auto.get(i).getColor(), auto.get(i).getPrice()
                                    ,motors.get(i).getMotorType(), motors.get(i).getVolume(), auto.get(i).getDrive()
                                    ,auto.get(i).getTransmission(), auto.get(i).getBodyStyle());
            listAutoJoin.add(autoJoin);
        }
        model.addAttribute("listAutoJoin", listAutoJoin);
        return "list-auto-join";
    }

   @GetMapping("/listAuto")
    public String displayListCars(Model model){
	    List<Auto> auto = autoService.getAllAuto();
        model.addAttribute("listAuto", auto);
        return "list-auto";
    }

    @GetMapping("/listBrand")
    public String displayListBrand(Model model){
	    List<Brand> brand = brandService.getAllBrand();
        model.addAttribute("listBrand", brand);
        return "list-brand";
    }

    @GetMapping("/listMotor")
    public String displayListMotor(Model model){
        List<Motor> motors = motorService.getAllMotor();
        model.addAttribute("listMotor", motors);
        return "list-motor";
    }
}

