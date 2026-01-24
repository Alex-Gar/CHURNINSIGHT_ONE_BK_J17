package com.churninsight.one.repositories;

import com.churninsight.one.models.historico.Historico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HistoricoRepository extends JpaRepository<Historico, Long> {


    Page<Historico> findAllByClienteIdClienteAndActivoTrue(String IdCliente, Pageable paguinas);


}
