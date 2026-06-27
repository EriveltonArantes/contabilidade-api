package com.contabilidadeapi.controller;

import com.contabilidadeapi.dto.PlanoContaRequestDTO;
import com.contabilidadeapi.dto.PlanoContaResponseDTO;
import com.contabilidadeapi.service.PlanoContaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "PlanoConta", description = "Gerenciamento de planocontas")
@RestController
@RequestMapping("/api/planocontas")
public class PlanoContaController {

    @Autowired
    private PlanoContaService service;

    @Operation(summary = "Listar todos os PlanoConta")
    @GetMapping
    public List<PlanoContaResponseDTO> listar(@RequestParam(required = false) String nome, @RequestParam(required = false) Long categoriaId) {
        List<PlanoContaResponseDTO> resultado = service.listar();
        if (nome != null && !nome.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getNome() != null &&
                item.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        if (categoriaId != null) {
            resultado = resultado.stream().filter(item -> categoriaId.equals(item.getCategoriaId())).collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @Operation(summary = "Buscar PlanoConta por ID")
    @GetMapping("/{id}")
    public PlanoContaResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar PlanoConta")
    @PostMapping
    public ResponseEntity<PlanoContaResponseDTO> criar(@Valid @RequestBody PlanoContaRequestDTO planoConta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(planoConta));
    }

    @Operation(summary = "Atualizar PlanoConta")
    @PutMapping("/{id}")
    public PlanoContaResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody PlanoContaRequestDTO planoConta) {
        return service.atualizar(id, planoConta);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir PlanoConta")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
