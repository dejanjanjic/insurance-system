package net.etfbl.sni.insurancesystem.repository;

import net.etfbl.sni.insurancesystem.model.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Long> {

}
