package com.churninsight.one.services;

import com.churninsight.one.clients.DsPredictClient;
import com.churninsight.one.models.cliente.Cliente;
import com.churninsight.one.models.cliente.dto.DatosConsultaChurnCliente;
import com.churninsight.one.models.cliente.dto.DatosDetalleChurnCliente;
import com.churninsight.one.models.cliente.dto.DatosObtenerPrediccionCliente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PredictService {

    @Autowired
    private DsPredictClient dsPredictClient;

    public DatosDetalleChurnCliente predecirCliente(@Valid DatosConsultaChurnCliente datos) {

        Cliente cliente = new Cliente(datos);
        var datosDS = dsPredictClient.obtenerPrediccion(new DatosObtenerPrediccionCliente(cliente));
        cliente.setChurn(datosDS.churn());
        cliente.setPrevision(datosDS.prevision());
        cliente.setProbabilidad(datosDS.probabilidad());
        return new DatosDetalleChurnCliente(cliente);
    }
}
