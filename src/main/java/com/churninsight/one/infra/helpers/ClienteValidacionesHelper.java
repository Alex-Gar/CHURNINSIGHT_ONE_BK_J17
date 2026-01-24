package com.churninsight.one.infra.helpers;

import com.churninsight.one.infra.exceptions.ex.DsPredictExchangeException;
import com.churninsight.one.infra.exceptions.ex.ResourceNotFoundException;
import com.churninsight.one.models.cliente.Cliente;
import com.churninsight.one.models.cliente.dto.DatosConsultaChurnCliente;
import com.churninsight.one.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClienteValidacionesHelper {

    private final ClienteRepository clienteRepository;

    public Cliente validaClienteExiste(String cliente) {

        Cliente nuevoCliente = clienteRepository.findByidClienteAndActivoTrue(cliente)
                .orElseThrow(
                        () -> new ResourceNotFoundException("El cliente no existe en el sistema.")
                );
        clienteRepository.save(nuevoCliente);
        return nuevoCliente;

    }

    public Cliente validaClienteExisteONo(DatosConsultaChurnCliente datos) {
        if (datos == null) return null;
        Optional<Cliente> cliente = clienteRepository.findByidClienteAndActivoTrue(datos.idCliente());
        if (cliente.isPresent()) {
            throw new DsPredictExchangeException("El cliente con el id: " + datos.idCliente() + " existe en el sistema.");
        }
        return new Cliente(datos);
    }


    public void validaClienteExisteIdString(Cliente cliente) {
        clienteRepository.findByidClienteAndActivoTrue(cliente.getIdCliente());
    }

}
