package com.churninsight.one.models.entities.relacionHistorial;

import com.churninsight.one.models.entities.historialPredicciones.HistorialPrediccion;
import com.churninsight.one.models.entities.prediccion.Prediccion;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "relacion_predicciones_historial")
public class RelacionPrediccionHistorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prediccion_id", nullable = false)
    private Prediccion prediccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "historial_id", nullable = false)
    private HistorialPrediccion historial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Prediccion getPrediccion() {
        return prediccion;
    }

    public void setPrediccion(Prediccion prediccion) {
        this.prediccion = prediccion;
    }

    public HistorialPrediccion getHistorial() {
        return historial;
    }

    public void setHistorial(HistorialPrediccion historial) {
        this.historial = historial;
    }

}