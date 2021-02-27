package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ncedu.entity.Auto;
import ru.ncedu.entity.Brand;
import ru.ncedu.entity.Motor;
import ru.ncedu.service.AutoService;
import ru.ncedu.service.BrandService;
import ru.ncedu.service.MotorService;

@Controller
@RequiredArgsConstructor
public class AddEditController {

    private final AutoService autoService;

    private final BrandService brandService;

    private final MotorService motorService;

    @GetMapping(value = {"/auto/add"})
    public String showAddAuto(Model model) {

        Brand brand = brandService.findBrandByIdBrand(new Auto().getIdBrand());
        Motor motor = motorService.findMotorByIdMotor(new Auto().getIdMotor());

        model.addAttribute("add", true);
        model.addAttribute("auto", new Auto());
        model.addAttribute("brand", brand);
        model.addAttribute("motor", motor);

        return "auto-edit";
    }

    @PostMapping(value = "/auto/add")
    public String addAuto(Model model, @ModelAttribute("auto") Auto auto) {

        try {
            auto.setDriveType(auto.getDrive().toString().toLowerCase());
            auto.setTransmissionType(auto.getTransmission().toString().toLowerCase());
            auto.setBodyStyleType(auto.getBody().toString().toLowerCase());
            Auto newAuto = autoService.save(auto);

            return "redirect:/auto/" + String.valueOf(newAuto.getId());

        } catch (Exception ex) {
            model.addAttribute("add", true);

            return "auto-edit";
        }
    }

    @GetMapping(value = "/auto/{autoId}")
    public String getShowAutoById(Model model, @PathVariable long autoId) {

        Auto auto = autoService.findById(autoId);
        Brand brand = brandService.findById(auto.getIdBrand());
        Motor motor = motorService.findById(auto.getIdMotor());

        model.addAttribute("auto", auto);
        model.addAttribute("brand", brand);
        model.addAttribute("motor", motor);

        return "auto";
    }

    @GetMapping(value = {"/auto/{autoId}/edit"})
    public String showEditAuto(Model model, @PathVariable long autoId) {

        Auto auto = autoService.findById(autoId);

        auto.setDriveType(auto.getDriveType().toString().toLowerCase());
        auto.setTransmissionType(auto.getTransmissionType().toString().toLowerCase());
        auto.setBodyStyleType(auto.getBodyStyleType().toString().toLowerCase());

        Brand brand = brandService.findById(auto.getIdBrand());
        Motor motor = motorService.findById(auto.getIdMotor());

        model.addAttribute("add", false);
        model.addAttribute("auto", auto);
        model.addAttribute("brand", brand);
        model.addAttribute("motor", motor);

        return "auto-edit";
    }

    @PostMapping(value = {"/auto/{Id}/edit"})
    public String updateAuto(Model model, @PathVariable long Id, @ModelAttribute("auto") Auto auto
            , @ModelAttribute("brand") Brand brand, @ModelAttribute("motor") Motor motor) {

        try {
            auto.setId(Id);
            auto.setDriveType(auto.getDrive().toString().toLowerCase());
            auto.setTransmissionType(auto.getTransmission().toString().toLowerCase());
            auto.setBodyStyleType(auto.getBody().toString().toLowerCase());
            autoService.update(auto);

            return "redirect:/auto/" + String.valueOf(auto.getId());

        } catch (Exception ex) {
            ex.printStackTrace();
            model.addAttribute("add", false);

            return "auto-edit";
        }

    }

    @GetMapping(value = {"/auto/{autoId}/delete"})
    public String showDeleteAutoById(Model model, @PathVariable long autoId) {

        Auto auto = autoService.findById(autoId);
        Brand brand = brandService.findBrandByIdBrand(auto.getIdBrand());
        Motor motor = motorService.findMotorByIdMotor(auto.getIdMotor());

        model.addAttribute("allowDelete", true);
        model.addAttribute("auto", auto);
        model.addAttribute("brand", brand);
        model.addAttribute("motor", motor);

        return "auto";
    }

    @PostMapping(value = {"/auto/{autoId}/delete"})
    public String deleteAutoById(Model model, @PathVariable long autoId) {

        Auto auto = autoService.findById(autoId);
        auto.setIdBrand(0L);
        auto.setIdMotor(0L);

        autoService.delete(autoId);

        return "redirect:/auto";
    }
}
