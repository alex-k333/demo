package com.provision.demo.repository;

import com.provision.demo.model.entity.MeterDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeterDeviceRepository extends JpaRepository<MeterDevice, Long> {
}
