package com.churninsight.one.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.churninsight.one.models.entities.oferta.Oferta;
import com.churninsight.one.models.entities.oferta.OfertaDto;
import com.churninsight.one.models.peyload.ApiResponse;
import com.churninsight.one.services.OfertaService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ofertas")
@Tag(name = "Ofertas", description = "Endpoints para la gestión de ofertas y retención")
public class OfertaController {

    @Autowired
    private OfertaService ofertaService;

    @GetMapping
    public ResponseEntity<ApiResponse> listarActivas(@RequestParam(defaultValue = "0") Integer pagina,
            @RequestParam(defaultValue = "10") Integer tamanio) {
        Page<Oferta> data = this.ofertaService.listarActivos(pagina, tamanio);
        ApiResponse response = new ApiResponse(data.get(), "Lista de ofertas obtenida con éxito", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/retencion")
    public ResponseEntity<ApiResponse> listarParaRetencion(@RequestParam(defaultValue = "0") Integer pagina,
            @RequestParam(defaultValue = "10") Integer tamanio) {
        Page<Oferta> data = this.ofertaService.listarOfertasChurn(pagina, tamanio);
        ApiResponse response = new ApiResponse(data.get(), "Lista de ofertas de retención obtenida con éxito", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> buscarPorId(@PathVariable Long id) {
        ApiResponse resultado = this.ofertaService.buscarActivoPorId(id);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> crear(@Valid @RequestBody OfertaDto ofertaDto) {
        ApiResponse nuevaOferta = this.ofertaService.crear(ofertaDto);
        return new ResponseEntity<>(nuevaOferta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editar(@PathVariable Long id, @Valid @RequestBody OfertaDto ofertaDto) {
        ApiResponse ofertaEditada = this.ofertaService.editar(id, ofertaDto);
        return new ResponseEntity<>(ofertaEditada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminar(@PathVariable Long id) {
        this.ofertaService.borradoLogico(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
