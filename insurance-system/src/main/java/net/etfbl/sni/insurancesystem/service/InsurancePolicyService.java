package net.etfbl.sni.insurancesystem.service;

import net.etfbl.sni.insurancesystem.model.InsurancePolicy;
import net.etfbl.sni.insurancesystem.repository.InsurancePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsurancePolicyService {
    private final InsurancePolicyRepository insurancePolicyRepository;

    @Autowired
    InsurancePolicyService(InsurancePolicyRepository insurancePolicyRepository){
        this.insurancePolicyRepository = insurancePolicyRepository;
    }

    public List<InsurancePolicy> getAllInsurancePolicies() {
        return insurancePolicyRepository.findAll();
    }
}
