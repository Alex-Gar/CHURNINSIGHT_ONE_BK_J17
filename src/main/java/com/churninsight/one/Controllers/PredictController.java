package com.churninsight.one.Controllers;

import com.churninsight.one.models.cliente.dto.DatosActualizarCliente;
import com.churninsight.one.models.cliente.dto.DatosConsultaChurnCliente;
import com.churninsight.one.models.cliente.dto.DatosDetalleChurnCliente;
import com.churninsight.one.models.cliente.dto.DatosListarClientesChurn;
import com.churninsight.one.models.historico.dto.DatosDetallesHistoricoChurnCliente;
import com.churninsight.one.services.ClienteService;
import com.churninsight.one.services.HistoricoService;
import com.churninsight.one.services.PredictService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/predict")
public class PredictController {

    @Autowired
    private PredictService predictService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private HistoricoService historicoService;

    @PostMapping(value = "/test")
    public ResponseEntity<DatosDetalleChurnCliente> predictConsultaJson(@Valid @RequestBody DatosConsultaChurnCliente datos) {
        var predict = predictService.predecirCliente(datos);
        return ResponseEntity.ok(predict);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<DatosDetalleChurnCliente> predictConsultaSave(@Valid @RequestBody DatosConsultaChurnCliente datos, UriComponentsBuilder ucBuilder) {
        DatosDetalleChurnCliente predict = predictService.predecirYPersistir(datos);
        var uri = ucBuilder.path("/{id}").buildAndExpand(predict.idCliente()).toUri();
        return ResponseEntity.created(uri).body(predict);
    }

    @GetMapping(value = "/{idCliente}")
    public ResponseEntity<DatosDetalleChurnCliente> buscarClientePorIdCliente(@PathVariable String idCliente) {
        var cliente = clienteService.buscarClienteIdString(idCliente);
        return ResponseEntity.ok(new DatosDetalleChurnCliente(cliente));
    }

    @GetMapping(path = {"", "/"})
    public ResponseEntity<Page<DatosListarClientesChurn>> listarClientes(@PageableDefault(size = 10, sort = "idCliente") Pageable paguinas){
        Page listaClientes = clienteService.listarClientesChurn(paguinas);
        return ResponseEntity.ok(listaClientes);
    }

    @PatchMapping(value = "/{idCliente}")
    public ResponseEntity<DatosDetalleChurnCliente> actualizarYRecalcular(@PathVariable String idCliente, @Valid @RequestBody DatosActualizarCliente datos) {
           var clienteActualizado = clienteService.actulizarYRecalcularCliente(idCliente, datos);
           return ResponseEntity.ok(new DatosDetalleChurnCliente(clienteActualizado));
    }

    @DeleteMapping(value = "/{idCliente}")
    public ResponseEntity borrarCliente(@PathVariable String idCliente) {
        clienteService.borrarCliente(idCliente);
        return  ResponseEntity.noContent().build();
    }










}
