package com.churninsight.one.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.churninsight.one.models.entities.relacionHistorial.RelacionPrediccionHistorial;
import com.churninsight.one.models.repositories.RelacionPrediccionHistorialRepository;
import com.churninsight.one.services.RelacionPrediccionHistorialService;

@Service
public class RelacionPrediccionHistorialServiceImpl implements RelacionPrediccionHistorialService {

    @Autowired
    private RelacionPrediccionHistorialRepository relacionPrediccionHistorialRepository;

    @Override
    public RelacionPrediccionHistorial guardarRelacion(RelacionPrediccionHistorial relacionPrediccionHistorial) {
        return relacionPrediccionHistorialRepository.save(relacionPrediccionHistorial);
    }

}
