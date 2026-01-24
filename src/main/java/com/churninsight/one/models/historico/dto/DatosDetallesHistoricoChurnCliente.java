package com.churninsight.one.models.historico.dto;

import com.churninsight.one.models.historico.Historico;

import java.time.LocalDateTime;

public record DatosDetallesHistoricoChurnCliente(

        LocalDateTime fechaPrediccion,
        Double probabilidad,
        Boolean churn

) {

    public DatosDetallesHistoricoChurnCliente(Historico historico) {
        this(
                historico.getFechaPrediccion(),
                historico.getProbabilidad(),
                historico.getChurn()
        );
    }

}
