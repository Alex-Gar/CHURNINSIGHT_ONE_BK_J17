package com.churninsight.one.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.churninsight.one.models.entities.vistas.outputModeloView;

import java.math.BigDecimal;

@Repository
public interface InputModeloViewRepository extends JpaRepository<outputModeloView, String> {

    @Query("SELECT AVG(u.cargoMensual) FROM outputModeloView u")
    BigDecimal averageMonthlyCharges();

    @Query("SELECT SUM(u.cargoMensual) FROM outputModeloView u")
    BigDecimal totalMonthlyCharges();

    @Query("SELECT AVG(u.antiguedadMeses) FROM outputModeloView u")
    Double averageTenure();
}
