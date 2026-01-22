package com.churninsight.one.infra.helpers;

import com.churninsight.one.infra.exceptions.ex.ResourceNotFoundException;
import com.churninsight.one.models.cliente.Cliente;
import com.churninsight.one.models.cliente.dto.DatosConsultaChurnCliente;
import com.churninsight.one.repositories.ClienteRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClienteValidacionesHelper {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente validaClienteExiste(String cliente) {

        Cliente nuevoCliente = clienteRepository.findByidClienteAndActivoTrue(cliente)
                .orElseThrow(
                        () -> new ResourceNotFoundException("El cliente no existe en el sistema.")
                );
        return nuevoCliente;
    }


    public Cliente validaClienteExisteOConstrulle(DatosConsultaChurnCliente datos) {
        if (datos == null) return null;
        Optional<Cliente> clienteOpcional = clienteRepository.findByidClienteAndActivoTrue(datos.idCliente());
        if (clienteOpcional.isPresent()) {
            throw new EntityExistsException("El cliente existe en el sistema.");
        }
        return new Cliente(datos);
    }


    public void validaClienteExisteIdString(Cliente cliente) {
        clienteRepository.findByidClienteAndActivoTrue(cliente.getIdCliente());
    }

}
