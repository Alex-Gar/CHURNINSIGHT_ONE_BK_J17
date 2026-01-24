package com.churninsight.one.Controllers;

import com.churninsight.one.models.historico.dto.DatosDetallesHistoricoChurnCliente;
import com.churninsight.one.services.HistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;

    @GetMapping(value = "/{idCliente}")
    public ResponseEntity<Page<DatosDetallesHistoricoChurnCliente>> historicoChurnCliente(@PathVariable String idCliente, @PageableDefault(size = 10, sort = "fechaPrediccion") Pageable paguinas) {
        Page listaHistoricoClientes = historicoService.listarHistoricoClientesChurn(idCliente, paguinas);
        return ResponseEntity.ok(listaHistoricoClientes);
    }

}
