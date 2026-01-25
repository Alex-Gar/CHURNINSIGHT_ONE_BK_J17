package com.churninsight.one.models.entities.vistas;

import jakarta.persistence.*;
import java.math.BigDecimal;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "vw_modelo_insumos")
public class ModeloInsumoView {

    @Id
    @Column(name = "id_cliente")
    private String idCliente;

    private String gender;

    @Column(name = "seniorcitizen")
    private String seniorCitizen;

    private String partner;

    private String dependents;

    private Integer tenure;

    @Column(name = "phoneservice")
    private String phoneService;

    @Column(name = "multiplelines")
    private String multipleLines;

    @Column(name = "internetservice")
    private String internetService;

    @Column(name = "onlinesecurity")
    private String onlineSecurity;

    @Column(name = "onlinebackup")
    private String onlineBackup;

    @Column(name = "deviceprotection")
    private String deviceProtection;

    @Column(name = "techsupport")
    private String techSupport;

    @Column(name = "streamingtv")
    private String streamingTV;

    @Column(name = "streamingmovies")
    private String streamingMovies;

    private String contract;

    @Column(name = "paperlessbilling")
    private String paperlessBilling;

    @Column(name = "paymentmethod")
    private String paymentMethod;

    @Column(name = "monthlycharges")
    private BigDecimal monthlyCharges;

    @Column(name = "totalcharges")
    private BigDecimal totalCharges;

    // Getters only since it's an immutable view
    public String getIdCliente() {
        return idCliente;
    }

    public String getGender() {
        return gender;
    }

    public String getSeniorCitizen() {
        return seniorCitizen;
    }

    public String getPartner() {
        return partner;
    }

    public String getDependents() {
        return dependents;
    }

    public Integer getTenure() {
        return tenure;
    }

    public String getPhoneService() {
        return phoneService;
    }

    public String getMultipleLines() {
        return multipleLines;
    }

    public String getInternetService() {
        return internetService;
    }

    public String getOnlineSecurity() {
        return onlineSecurity;
    }

    public String getOnlineBackup() {
        return onlineBackup;
    }

    public String getDeviceProtection() {
        return deviceProtection;
    }

    public String getTechSupport() {
        return techSupport;
    }

    public String getStreamingTV() {
        return streamingTV;
    }

    public String getStreamingMovies() {
        return streamingMovies;
    }

    public String getContract() {
        return contract;
    }

    public String getPaperlessBilling() {
        return paperlessBilling;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public BigDecimal getMonthlyCharges() {
        return monthlyCharges;
    }

    public BigDecimal getTotalCharges() {
        return totalCharges;
    }
}
