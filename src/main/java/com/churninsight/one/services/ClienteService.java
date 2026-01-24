package com.churninsight.one.services;

import com.churninsight.one.clients.DsPredictClient;
import com.churninsight.one.infra.helpers.ClienteValidacionesHelper;
import com.churninsight.one.models.cliente.Cliente;
import com.churninsight.one.models.cliente.dto.DatosActualizarCliente;
import com.churninsight.one.models.cliente.dto.DatosDetalleChurnCliente;
import com.churninsight.one.models.cliente.dto.DatosListarClientesChurn;
import com.churninsight.one.models.cliente.dto.DatosObtenerPrediccionCliente;
import com.churninsight.one.models.historico.Historico;
import com.churninsight.one.repositories.ClienteRepository;
import com.churninsight.one.repositories.HistoricoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final ClienteValidacionesHelper clienteValidacionesHelper;

    private final HistoricoRepository historicoRepository;

    private final DsPredictClient dsPredictClient;

    public DatosDetalleChurnCliente buscarClienteIdString(String idCliente) {
        var cliente = clienteValidacionesHelper.validaClienteExiste(idCliente);
        return new DatosDetalleChurnCliente(cliente);
    }

    public Page<DatosListarClientesChurn> listarClientesChurn(Pageable pageable) {
        Page<DatosListarClientesChurn> page = clienteRepository.findAllByActivoTrue(pageable).map(
                DatosListarClientesChurn::new
        );
        return page;
    }

    @Transactional
    public DatosDetalleChurnCliente actualizarYRecalcularCliente(String idCliente, DatosActualizarCliente datos) {
        Cliente cliente = clienteValidacionesHelper.validaClienteExiste(idCliente);
        cliente.actualizar(datos);
        try {
            var prediccion = dsPredictClient.obtenerPrediccion(new DatosObtenerPrediccionCliente(cliente));
            cliente.setChurn(prediccion.churn());
            cliente.setPrevision(prediccion.prevision());
            cliente.setProbabilidad(prediccion.probabilidad());
            historicoRepository.save(new Historico(cliente));
        } catch (Exception e) {
            throw new RuntimeException("No se pudo actualizar el cliente porque el servicio de IA falló. Intente de nuevo.");
        }
        return new DatosDetalleChurnCliente(cliente);
    }

    @Transactional
    public void borrarCliente(String idCliente) {
        Cliente cliente = clienteValidacionesHelper.validaClienteExiste(idCliente);
        cliente.setActivo(false);
        clienteRepository.save(cliente);
    }
}

