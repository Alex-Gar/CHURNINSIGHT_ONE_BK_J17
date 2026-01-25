package com.churninsight.one.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.churninsight.one.models.entities.plan.Plan;
import com.churninsight.one.models.entities.plan.PlanDto;
import com.churninsight.one.models.peyload.ApiResponse;
import com.churninsight.one.services.PlanService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/planes")
@Tag(name = "Planes", description = "Endpoints para la gestión de planes de servicio")
public class PlanController {

    @Autowired
    private PlanService planService;

    @GetMapping
    public ResponseEntity<ApiResponse> listarActivos(@RequestParam(defaultValue = "0") Integer pagina,
            @RequestParam(defaultValue = "10") Integer tamanio) {
        Page<Plan> data = this.planService.listarActivos(pagina, tamanio);
        ApiResponse response = new ApiResponse(data.get(), "Lista de planes obtenida con éxito", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/eliminados")
    public ResponseEntity<ApiResponse> listarEliminados(@RequestParam(defaultValue = "0") Integer pagina,
            @RequestParam(defaultValue = "10") Integer tamanio) {
        Page<Plan> data = this.planService.listarEliminados(pagina, tamanio);
        ApiResponse response = new ApiResponse(data.get(), "Lista de planes eliminados obtenida con éxito", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> buscarPorId(@PathVariable Long id) {
        ApiResponse resultado = this.planService.buscarActivoPorId(id);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> crear(@Valid @RequestBody PlanDto planDto) {
        ApiResponse nuevoPlan = this.planService.crear(planDto);
        return new ResponseEntity<>(nuevoPlan, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editar(@PathVariable Long id, @Valid @RequestBody PlanDto planDto) {
        ApiResponse planEditado = this.planService.editar(id, planDto);
        return new ResponseEntity<>(planEditado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminar(@PathVariable Long id) {
        this.planService.borradoLogico(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
