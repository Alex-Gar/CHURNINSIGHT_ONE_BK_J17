package com.churninsight.one.models.cliente;

import com.churninsight.one.enums.Genero;
import com.churninsight.one.enums.MetodoPago;
import com.churninsight.one.enums.ServicioInternet;
import com.churninsight.one.enums.TipoContrato;
import com.churninsight.one.models.cliente.dto.DatosConsultaChurnCliente;
import com.churninsight.one.models.historico.Historico;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Table(name = "clientes")
@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_cliente", unique = true)
    private String idCliente;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genero genero;

    @Column(name = "adulto_mayor", nullable = false)
    private Boolean adultoMayor;

    @Column(name = "tiene_pareja", nullable = false)
    private Boolean tienePareja;

    @Column(name = "tiene_dependientes", nullable = false)
    private Boolean tieneDependientes;

    @Column(name = "antiguedad_meses", nullable = false)
    private Integer antiguedadMeses;

    @Column(name = "servicio_telefono", nullable = false)
    private Boolean servicioTelefono;

    @Column(name = "lineas_multiples", nullable = false)
    private Boolean lineasMultiples;

    @Enumerated(EnumType.STRING)
    @Column(name = "servicio_internet", nullable = false)
    private ServicioInternet servicioInternet;

    @Column(name = "seguridad_en_linea", nullable = false)
    private Boolean seguridadEnLinea;

    @Column(name = "respaldo_en_linea",nullable = false)
    private Boolean respaldoEnLinea;

    @Column(name = "proteccion_dispositivo", nullable = false)
    private Boolean proteccionDispositivo;

    @Column(name = "soporte_tecnico", nullable = false)
    private Boolean soporteTecnico;

    @Column(name = "streaming_tv", nullable = false)
    private Boolean streamingTv;

    @Column(name = "streaming_peliculas")
    private Boolean streamingPeliculas;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_contrato", nullable = false)
    private TipoContrato tipoContrato;

    @Column(name = "facturacion_electronica")
    private Boolean facturacionElectronica;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false)
    private MetodoPago metodoPago;

    @Column(name = "cargo_mensual", nullable = false)
    private BigDecimal cargoMensual;

    @Column(name = "cargos_totales", nullable = false)
    private BigDecimal cargosTotales;

    private String prevision;

    private Double probabilidad;

    private Boolean churn;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Historico> historialChurn = new ArrayList<>();

    private Boolean activo;

    public Cliente(DatosConsultaChurnCliente datos) {
        this.id = null;
        this.idCliente = datos.idCliente();
        this.genero = datos.genero();
        this.adultoMayor = datos.adultoMayor();
        this.tienePareja = datos.tienePareja();
        this.tieneDependientes = datos.tieneDependientes();
        this.antiguedadMeses = datos.antiguedadMeses();
        this.servicioTelefono = datos.servicioTelefono();
        this.lineasMultiples = datos.lineasMultiples();
        this.servicioInternet = datos.servicioInternet();
        this.seguridadEnLinea = datos.seguridadEnLinea();
        this.respaldoEnLinea = datos.respaldoEnLinea();
        this.proteccionDispositivo = datos.proteccionDispositivo();
        this.soporteTecnico = datos.soporteTecnico();
        this.streamingTv = datos.streamingTv();
        this.streamingPeliculas = datos.streamingPeliculas();
        this.tipoContrato = datos.tipoContrato();
        this.facturacionElectronica = datos.facturacionElectronica();
        this.metodoPago = datos.metodoPago();
        this.cargoMensual = datos.cargoMensual();
        this.cargosTotales = datos.cargosTotales();
        this.activo = true;
    }
}
