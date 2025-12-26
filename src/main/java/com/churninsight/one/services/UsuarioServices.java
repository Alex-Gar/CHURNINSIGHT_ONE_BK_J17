package com.churninsight.one.services;

import com.churninsight.one.models.peyload.ApiResponse;
import com.churninsight.one.models.peyload.PrediccionRequestDTO;

public interface UsuarioServices {

    ApiResponse predecirChurn(PrediccionRequestDTO request);
}
