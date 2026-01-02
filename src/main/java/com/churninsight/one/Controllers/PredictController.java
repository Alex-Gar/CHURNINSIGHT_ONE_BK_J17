package com.churninsight.one.Controllers;

import com.churninsight.one.models.cliente.dto.DatosConsultaChurnCliente;
import com.churninsight.one.models.cliente.dto.DatosDetalleChurnCliente;
import com.churninsight.one.services.PredictService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/predict")
public class PredictController {

    @Autowired
    private PredictService predictService;

    @PostMapping
    public ResponseEntity<DatosDetalleChurnCliente> predictConsultaJson(@Valid @RequestBody DatosConsultaChurnCliente datos) {
        var predict = predictService.predecirCliente(datos);
        return ResponseEntity.ok(predict);
    }

}
