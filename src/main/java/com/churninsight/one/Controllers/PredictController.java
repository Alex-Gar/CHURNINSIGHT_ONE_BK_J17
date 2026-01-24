package com.churninsight.one.Controllers;

import com.churninsight.one.models.cliente.dto.DatosConsultaChurnCliente;
import com.churninsight.one.models.cliente.dto.DatosDetalleChurnCliente;
import com.churninsight.one.services.PredictService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/predict")
@RequiredArgsConstructor
public class PredictController {


    private final PredictService predictService;

    @PostMapping(value = "/consultaSimple")
    public ResponseEntity<DatosDetalleChurnCliente> consultaClienteChurnLibre(@Valid @RequestBody DatosConsultaChurnCliente datos) {
        var predict = predictService.predecirCliente(datos);
        return ResponseEntity.ok(predict);
    }

    @PostMapping
    public ResponseEntity<DatosDetalleChurnCliente> registroClienteConConsultaChurn(@Valid @RequestBody DatosConsultaChurnCliente datos) {
        var predict = predictService.predecirYPersistir(datos);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/cliente/{id}").buildAndExpand(predict.idCliente()).toUri();
        return ResponseEntity.created(uri).body(predict);
    }

}
