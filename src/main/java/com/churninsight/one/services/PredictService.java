package com.churninsight.one.services;

import com.churninsight.one.clients.DsPredictClient;
import com.churninsight.one.infra.helpers.ClienteValidacionesHelper;
import com.churninsight.one.models.cliente.Cliente;
import com.churninsight.one.models.cliente.dto.DatosConsultaChurnCliente;
import com.churninsight.one.models.cliente.dto.DatosDetalleChurnCliente;
import com.churninsight.one.models.cliente.dto.DatosObtenerPrediccionCliente;
import com.churninsight.one.repositories.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PredictService {

    @Autowired
    private DsPredictClient dsPredictClient;

    @Autowired
    private ClienteValidacionesHelper clienteValidacionesHelper;

    @Autowired
    private ClienteRepository clienteRepository;

    public DatosDetalleChurnCliente predecirCliente(@Valid DatosConsultaChurnCliente datos) {
        Cliente cliente = new Cliente(datos);
        var datosDS = dsPredictClient.obtenerPrediccion(new DatosObtenerPrediccionCliente(cliente));
        cliente.setChurn(datosDS.churn());
        cliente.setPrevision(datosDS.prevision());
        cliente.setProbabilidad(datosDS.probabilidad());
        return new DatosDetalleChurnCliente(cliente);
    }

    public DatosDetalleChurnCliente predecirYPersistir(@Valid DatosConsultaChurnCliente datos) {
        Cliente cliente = clienteValidacionesHelper.validaClienteExisteONo(datos);
        clienteRepository.save(cliente);
        try {
            var prediccion = dsPredictClient.obtenerPrediccion(new DatosObtenerPrediccionCliente(cliente));
            cliente.setChurn(prediccion.churn());
            cliente.setPrevision(prediccion.prevision());
            cliente.setProbabilidad(prediccion.probabilidad());
            clienteRepository.save(cliente);

        } catch (Exception e) {
            throw new RuntimeException("El cliente se creó con ID: " + cliente.getId() +
                    ", pero la predicción falló: " + e.getMessage());
        }

        return new DatosDetalleChurnCliente(cliente);
    }
}
