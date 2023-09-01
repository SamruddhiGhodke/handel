package com.example.jwtProject.Service;

import com.example.jwtProject.Entity.DomesticMaterialEntity;
import com.example.jwtProject.Entity.InternationalMaterialEntity;
import com.example.jwtProject.Entity.RegistrationEntity;
import com.example.jwtProject.Model.DomesticModel;
import com.example.jwtProject.Model.InternationalModel;
import com.example.jwtProject.Model.JwtModel;
import com.example.jwtProject.Repository.DomesticRepo;
import com.example.jwtProject.Repository.InternationalRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MaterialService {
    @Autowired
    public DomesticRepo domesticRepo;
    @Autowired
    public InternationalRepo internationalRepo;
    public DomesticMaterialEntity createDomestic(DomesticModel domesticModel) {
        Long domesticMaterialId = domesticModel.getDomesticMaterialId();

        if (domesticMaterialId != null && domesticMaterialId != 0) {
            DomesticMaterialEntity domesticMaterialEntity = domesticRepo.findByDomesticMaterialId(domesticMaterialId);
            domesticMaterialEntity.setMaterialName(domesticModel.getMaterialName());
            domesticMaterialEntity.setQuantity(domesticModel.getQuantity());
            domesticMaterialEntity.setCreditPeriod(domesticModel.getCreditPeriod());
            domesticMaterialEntity.setUnitPrice(domesticModel.getUnitPrice());
            domesticMaterialEntity.setSupplierName(domesticModel.getSupplierName());

            return domesticRepo.save(domesticMaterialEntity);
        }
        else {

        DomesticMaterialEntity domesticMaterial = new DomesticMaterialEntity();
        domesticMaterial.setMaterialName(domesticModel.getMaterialName());
        domesticMaterial.setQuantity(domesticModel.getQuantity());
        domesticMaterial.setCreditPeriod(domesticModel.getCreditPeriod());
        domesticMaterial.setUnitPrice(domesticModel.getUnitPrice());
        domesticMaterial.setSupplierName(domesticModel.getSupplierName());

            return domesticRepo.save(domesticMaterial);
        }
    }

    public InternationalMaterialEntity createInternational(InternationalModel internationalModel) {
        Long internationalId = internationalModel.getInternationalMaterialId();

        if (internationalId != null && internationalId != 0) {
            InternationalMaterialEntity internationalMaterialEntity = internationalRepo.findByInternationalMaterialId(internationalId);
            internationalMaterialEntity.setMaterialName(internationalModel.getMaterialName());
            internationalMaterialEntity.setQuantity(internationalModel.getQuantity());
            internationalMaterialEntity.setCreditPeriod(internationalModel.getCreditPeriod());
            internationalMaterialEntity.setUnitPrice(internationalModel.getUnitPrice());
            internationalMaterialEntity.setSupplierName(internationalModel.getSupplierName());

            return internationalRepo.save(internationalMaterialEntity);
        }
        else {

            InternationalMaterialEntity internationalMaterial = new InternationalMaterialEntity();
            internationalMaterial.setMaterialName(internationalModel.getMaterialName());
            internationalMaterial.setQuantity(internationalModel.getQuantity());
            internationalMaterial.setCreditPeriod(internationalModel.getCreditPeriod());
            internationalMaterial.setUnitPrice(internationalModel.getUnitPrice());
            internationalMaterial.setSupplierName(internationalModel.getSupplierName());

            return internationalRepo.save(internationalMaterial);
        }
    }
}
