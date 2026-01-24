package com.churninsight.one.services;

import com.churninsight.one.models.historico.dto.DatosDetallesHistoricoChurnCliente;
import com.churninsight.one.repositories.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository;

    public Page<DatosDetallesHistoricoChurnCliente> listarHistoricoClientesChurn(String IdCliente, Pageable paguinas) {
        Page page = historicoRepository.findAllByClienteIdClienteAndActivoTrue(IdCliente, paguinas).map(
                DatosDetallesHistoricoChurnCliente::new
        );
        return page;
    }
}
