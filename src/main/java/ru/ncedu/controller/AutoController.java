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
public class AutoController {
    private final int ROW_PER_PAGE = 10;

    @Autowired	
    AutoService autoService;	

    @Autowired	
    BrandService brandService;	

    @Autowired	
    MotorService motorService;	

    @GetMapping("/auto")
    public String displayListCarsJoin(Model model,  @RequestParam(value = "page", defaultValue = "1") int pageNumber){
        List<Auto> auto = autoService.findAll(pageNumber, ROW_PER_PAGE);
        List<Brand> brand = brandService.findAll(pageNumber, ROW_PER_PAGE);
        List<Motor> motors = motorService.findAll(pageNumber, ROW_PER_PAGE);
        List<AutoJoin> listAutoJoin = new ArrayList<>();	
        AutoJoin autoJoin;
        for(int i = 0; i < auto.size(); i++){	
            autoJoin = new AutoJoin(auto.get(i).getId(), brand.get(i).getNameBrand(), brand.get(i).getNameModel()	
                                    ,brand.get(i).getYear(), auto.get(i).getColor(), auto.get(i).getPrice()	
                                    ,motors.get(i).getMotorType(), motors.get(i).getVolume(), auto.get(i).getDrive()	
                                    ,auto.get(i).getTransmission(), auto.get(i).getBodyStyle());	
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