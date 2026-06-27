package com.contabilidadeapi.controller;

import com.contabilidadeapi.dto.CentroCustoRequestDTO;
import com.contabilidadeapi.dto.CentroCustoResponseDTO;
import com.contabilidadeapi.service.CentroCustoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "CentroCusto", description = "Gerenciamento de centrocustos")
@RestController
@RequestMapping("/api/centrocustos")
public class CentroCustoController {

    @Autowired
    private CentroCustoService service;

    @Operation(summary = "Listar todos os CentroCusto")
    @GetMapping
    public List<CentroCustoResponseDTO> listar(@RequestParam(required = false) String nome) {
        List<CentroCustoResponseDTO> resultado = service.listar();
        if (nome != null && !nome.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getNome() != null &&
                item.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @Operation(summary = "Buscar CentroCusto por ID")
    @GetMapping("/{id}")
    public CentroCustoResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar CentroCusto")
    @PostMapping
    public ResponseEntity<CentroCustoResponseDTO> criar(@Valid @RequestBody CentroCustoRequestDTO centroCusto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(centroCusto));
    }

    @Operation(summary = "Atualizar CentroCusto")
    @PutMapping("/{id}")
    public CentroCustoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody CentroCustoRequestDTO centroCusto) {
        return service.atualizar(id, centroCusto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir CentroCusto")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
