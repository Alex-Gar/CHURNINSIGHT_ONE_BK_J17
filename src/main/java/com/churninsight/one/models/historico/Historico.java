package com.churninsight.one.models.historico;

import com.churninsight.one.models.cliente.Cliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "historicos")
@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "fecha_prediccion")
    private LocalDateTime fechaPrediccion;

    private Boolean churn;

    private Double probabilidad;

    private Boolean activo;

    public Historico(Cliente cliente) {
        id = null;
        this.cliente = cliente;
        this.fechaPrediccion = LocalDateTime.now();
        this.probabilidad = cliente.getProbabilidad();
        this.churn = cliente.getChurn();
        this.activo = true;
    }

}
