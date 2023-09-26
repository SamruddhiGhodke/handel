package com.example.jwtProject.Controller;

import com.example.jwtProject.Entity.DomesticMaterialEntity;
import com.example.jwtProject.Entity.InternationalMaterialEntity;
import com.example.jwtProject.Model.DomesticModel;
import com.example.jwtProject.Model.InternationalModel;
import com.example.jwtProject.Service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/material")
public class MaterialController {
    @Autowired
    public MaterialService materialService;

    // API for creating a domestic material entity
    @PostMapping("/domesticMaterial")
    public DomesticMaterialEntity createDomestic(@RequestBody DomesticModel domesticModel){
        return materialService.createDomestic(domesticModel);
    }

    // API for creating an international material entity
    @PostMapping("/internationalMaterial")
    public InternationalMaterialEntity createInternational(@RequestBody InternationalModel internationalModel){
        return materialService.createInternational(internationalModel);
    }
}
