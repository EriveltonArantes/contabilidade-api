package com.contabilidadeapi.controller;

import com.contabilidadeapi.dto.CategoriaRequestDTO;
import com.contabilidadeapi.dto.CategoriaResponseDTO;
import com.contabilidadeapi.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Categoria", description = "Gerenciamento de categorias")
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @Operation(summary = "Listar todos os Categoria")
    @GetMapping
    public List<CategoriaResponseDTO> listar(@RequestParam(required = false) String nome) {
        List<CategoriaResponseDTO> resultado = service.listar();
        if (nome != null && !nome.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getNome() != null &&
                item.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @Operation(summary = "Buscar Categoria por ID")
    @GetMapping("/{id}")
    public CategoriaResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar Categoria")
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> criar(@Valid @RequestBody CategoriaRequestDTO categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(categoria));
    }

    @Operation(summary = "Atualizar Categoria")
    @PutMapping("/{id}")
    public CategoriaResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody CategoriaRequestDTO categoria) {
        return service.atualizar(id, categoria);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir Categoria")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
