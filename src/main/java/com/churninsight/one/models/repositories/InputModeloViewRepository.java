package com.churninsight.one.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.churninsight.one.models.entities.vistas.outputModeloView;

@Repository
public interface InputModeloViewRepository extends JpaRepository<outputModeloView, String> {

}
