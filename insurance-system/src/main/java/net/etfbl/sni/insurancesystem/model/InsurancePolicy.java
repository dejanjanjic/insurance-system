package net.etfbl.sni.insurancesystem.model;

import jakarta.persistence.*;

@Entity
public class InsurancePolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double price;
    private String type;
}
