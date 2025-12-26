package com.churninsight.one.services.implementations;


import com.churninsight.one.models.entities.Usuario;
import com.churninsight.one.models.peyload.ApiResponse;
import com.churninsight.one.models.peyload.PrediccionRequestDTO;
import com.churninsight.one.models.peyload.PrediccionResponseDTO;
import com.churninsight.one.services.UsuarioServices;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioServices {

    @Override
    public ApiResponse predecirChurn(PrediccionRequestDTO request) {

        //Simulación de respuesta de Python(por ahora)
        PrediccionResponseDTO respuesta= new PrediccionResponseDTO();
        respuesta.setAbandonoCliente(1);
        respuesta.setProbabilidadAbandono(0.73);

        //Mapear a entidad Usuario
        Usuario usuario = new Usuario();
        usuario.setIdCliente(request.getIdCliente());
        usuario.setGenero(request.getGenero());
        usuario.setAntiguedad_meses(request.getAntiguedadMeses());
        usuario.setTipo_contrato(request.getTipoContrato());
        usuario.setAbandono_cliente(respuesta.getAbandonoCliente());
        usuario.setProbabilidadAbandono(respuesta.getProbabilidadAbandono());

        return new ApiResponse("Predicción generada correctamente", respuesta);
    }
}
