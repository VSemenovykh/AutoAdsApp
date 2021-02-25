package ru.ncedu.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ncedu.entity.Auto;
import ru.ncedu.entity.Brand;
import ru.ncedu.entity.Motor;
import ru.ncedu.exception.ResourceNotFoundException;
import ru.ncedu.service.AutoService;
import ru.ncedu.service.BrandService;
import ru.ncedu.service.MotorService;

@Controller
@Slf4j
public class AddEditController{

    @Autowired
    AutoService autoService;

    @Autowired
    BrandService brandService;

    @Autowired
    MotorService motorService;

    @GetMapping(value = {"/auto/add"})
    public String showAddAuto(Model model) {

        Brand brand = brandService.findBrandByIdBrand( new Auto().getIdBrand());
        Motor motor = motorService.findMotorByIdMotor( new Auto().getIdMotor());

        model.addAttribute("add", true);
        model.addAttribute("auto",  new Auto());
        model.addAttribute("brand", brand);
        model.addAttribute("motor", motor);

        return "auto-edit";
    }

    @PostMapping(value = "/auto/add")
    public String addAuto(Model model,  @ModelAttribute("auto") Auto auto) {

        try {
            auto.setDriveType(auto.getDrive().toString());
            auto.setTransmissionType(auto.getTransmission().toString());
            auto.setBodyStyleType(auto.getBody().toString());
            Auto newAuto = autoService.save(auto);

            return "redirect:/auto/" + String.valueOf(newAuto.getId());

        }catch (Exception ex) {
            // log exception first,
            // then show error
            String errorMessage = ex.getMessage();
            log.error(errorMessage);

            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", true);

            return "auto-edit";
        }
    }

    @GetMapping(value = "/auto/{autoId}")
    public String getShowAutoById(Model model, @PathVariable long autoId) throws ResourceNotFoundException {

        Auto auto = null;
        Brand brand = null;
        Motor motor = null;

        try {
            auto = autoService.findById(autoId);
            brand = brandService.findById(auto.getIdBrand());
            motor = motorService.findById(auto.getIdMotor());

        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "Contact not found");
        }

        model.addAttribute("auto", auto);
        model.addAttribute("brand", brand);
        model.addAttribute("motor", motor);

        return "auto";
    }

    @GetMapping(value = {"/auto/{autoId}/edit"})
    public String showEditAuto(Model model, @PathVariable long autoId) throws ResourceNotFoundException {

        Auto auto = null;
        Brand brand = null;
        Motor motor = null;

        try {
            auto = autoService.findById(autoId);

            auto.setDriveType(auto.getDriveType());
            auto.setTransmissionType(auto.getTransmissionType());
            auto.setBodyStyleType(auto.getBodyStyleType());

            brand = brandService.findById(auto.getIdBrand());
            motor =  motorService.findById(auto.getIdMotor());

        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "auto not found");
        }

        model.addAttribute("add", false);
        model.addAttribute("auto", auto);
        model.addAttribute("brand", brand);
        model.addAttribute("motor", motor);

        return "auto-edit";
    }

    @PostMapping(value = {"/auto/{Id}/edit"})
    public String updateAuto(Model model, @PathVariable long Id, @ModelAttribute("auto") Auto auto
                            ,@ModelAttribute("brand") Brand brand, @ModelAttribute("motor") Motor motor) {

        try {
            auto.setId(Id);
            auto.setDriveType(auto.getDrive().toString().toLowerCase());
            auto.setTransmissionType(auto.getTransmission().toString().toLowerCase());
            auto.setBodyStyleType(auto.getBody().toString().toLowerCase());
            autoService.update(auto);

            return "redirect:/auto/" + String.valueOf(auto.getId());

        } catch (Exception ex) {
            // log exception first,
            // then show error
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", false);

            return "auto-edit";
        }
    }

    @GetMapping(value = {"/auto/{autoId}/delete"})
    public String showDeleteAutoById(Model model, @PathVariable long autoId) throws ResourceNotFoundException {

        Auto auto = null;
        Brand brand = null;
        Motor motor = null;

        try {
            auto = autoService.findById(autoId);
            brand = brandService.findBrandByIdBrand(auto.getIdBrand());
            motor = motorService.findMotorByIdMotor(auto.getIdMotor());

        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "Auto not found");
        }

        model.addAttribute("allowDelete", true);
        model.addAttribute("auto", auto);
        model.addAttribute("brand", brand);
        model.addAttribute("motor", motor);

        return "auto";
    }

    @SneakyThrows
    @PostMapping(value = {"/auto/{autoId}/delete"})
    public String deleteAutoById(Model model, @PathVariable long autoId) throws ResourceNotFoundException {

        try {
            Auto auto = autoService.findById(autoId);
            auto.setIdBrand(0L);
            auto.setIdMotor(0L);

            autoService.delete(autoId);

            return "redirect:/auto";
        } catch (ResourceNotFoundException ex) {

            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);

            return "auto";
        }
    }
}
