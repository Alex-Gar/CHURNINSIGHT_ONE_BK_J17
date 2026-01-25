package com.churninsight.one.mappers;

import com.churninsight.one.models.entities.vistas.outputModelViewDto;
import com.churninsight.one.models.entities.vistas.outputModeloView;
import static com.churninsight.one.utils.BooleanUtils.siNo;

public class InputModelViewMapper {

    private InputModelViewMapper() {

    }

    public static outputModelViewDto toDto(outputModeloView u) {
        return new outputModelViewDto(
                u.getIdCliente(),
                u.getGenero(),
                u.getAdultoMayor(),
                siNo(u.getTienePareja()),
                siNo(u.getTieneDependientes()),
                u.getAntiguedadMeses(),
                siNo(u.getServicioTelefono()),
                siNo(u.getLineasMultiples()),
                u.getServicioInternet(),
                siNo(u.getSeguridadEnLinea()),
                siNo(u.getRespaldoEnLinea()),
                siNo(u.getProteccionDispositivo()),
                siNo(u.getSoporteTecnico()),
                siNo(u.getStreamingTv()),
                siNo(u.getStreamingPeliculas()),
                u.getTipoContrato(),
                siNo(u.getFacturacionElectronica()),
                u.getMetodoPago(),
                u.getCargoMensual(),
                u.getCargosTotales());
    }
}
