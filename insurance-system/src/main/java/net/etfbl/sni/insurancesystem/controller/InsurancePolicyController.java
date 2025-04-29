package net.etfbl.sni.insurancesystem.controller;

import net.etfbl.sni.insurancesystem.model.InsurancePolicy;
import net.etfbl.sni.insurancesystem.service.InsurancePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/insurancePolicy")
public class InsurancePolicyController {
    private final InsurancePolicyService insurancePolicyService;

    @Autowired
    InsurancePolicyController(InsurancePolicyService insurancePolicyService){
        this.insurancePolicyService = insurancePolicyService;
    }

    @GetMapping
    public ResponseEntity<List<InsurancePolicy>> getAllInsurancePolicies(){
        List<InsurancePolicy> insurancePolicies = insurancePolicyService.getAllInsurancePolicies();
        return ResponseEntity.ok(insurancePolicies);
    }
}
