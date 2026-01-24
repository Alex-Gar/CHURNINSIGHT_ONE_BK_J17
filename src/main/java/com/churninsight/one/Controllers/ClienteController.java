package com.churninsight.one.Controllers;

import com.churninsight.one.models.cliente.dto.DatosActualizarCliente;
import com.churninsight.one.models.cliente.dto.DatosDetalleChurnCliente;
import com.churninsight.one.models.cliente.dto.DatosListarClientesChurn;
import com.churninsight.one.services.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Page<DatosListarClientesChurn>> listarClientes(@PageableDefault(size = 10, sort = "idCliente") Pageable pageable) {
        var listaClientes = clienteService.listarClientesChurn(pageable);
        return ResponseEntity.ok(listaClientes);
    }

    @GetMapping(value = "/{idCliente}")
    public ResponseEntity<DatosDetalleChurnCliente> obtenerPorId(@PathVariable String idCliente) {
        var cliente = clienteService.buscarClienteIdString(idCliente);
        return ResponseEntity.ok(cliente);
    }

    @PatchMapping(value = "/{idCliente}")
    public ResponseEntity<DatosDetalleChurnCliente> actualizarYRecalcular(@PathVariable String idCliente, @Valid @RequestBody DatosActualizarCliente datos) {
        var clienteActualizado = clienteService.actualizarYRecalcularCliente(idCliente, datos);
        return ResponseEntity.ok(clienteActualizado);
    }

    @DeleteMapping(value = "/{idCliente}")
    public ResponseEntity<Void> eliminar(@PathVariable String idCliente) {
        clienteService.borrarCliente(idCliente);
        return ResponseEntity.noContent().build();
    }

}
