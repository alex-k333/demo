package com.provision.demo.model.entity;

import com.provision.demo.model.Id;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "meter_devices")
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class MeterDevice implements Id<Long> {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String serialNumber;

    @Column
    private String inventoryNumber;

    @Column
    private Short manufactureYear;

    @Column
    private Integer transformationCoefficient;

    @Temporal(TemporalType.DATE)
    @Column
    private LocalDate installationDate;

    @Column
    private String sealNumber;

    @Column
    private String antiMagneticSealNumber;

    @Column
    private String installationPlace;

    @Column(length = 5000)
    private String note;

    @Column(unique = true)
    private UUID gisHousingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "meterDevice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ZoneValue> zoneValues;
}
