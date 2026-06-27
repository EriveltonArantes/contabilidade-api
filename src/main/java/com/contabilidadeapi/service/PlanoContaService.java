package com.contabilidadeapi.service;

import com.contabilidadeapi.dto.PlanoContaRequestDTO;
import com.contabilidadeapi.dto.PlanoContaResponseDTO;
import com.contabilidadeapi.exception.ResourceNotFoundException;
import com.contabilidadeapi.mapper.PlanoContaMapper;
import com.contabilidadeapi.model.PlanoConta;
import com.contabilidadeapi.repository.PlanoContaRepository;
import com.contabilidadeapi.repository.CategoriaRepository;
import com.contabilidadeapi.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlanoContaService {

    @Autowired
    private PlanoContaRepository repository;

    @Autowired
    private PlanoContaMapper mapper;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<PlanoContaResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PlanoContaResponseDTO buscar(Long id) {
        PlanoConta entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("PlanoConta não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public PlanoContaResponseDTO criar(PlanoContaRequestDTO dto) {
        PlanoConta entity = mapper.toEntity(dto);
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
            .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrado com id: " + dto.getCategoriaId()));
        entity.setCategoria(categoria);
        PlanoConta salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public PlanoContaResponseDTO atualizar(Long id, PlanoContaRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("PlanoConta não encontrado com id: " + id);
        }
        PlanoConta entity = mapper.toEntity(dto);
        entity.setId(id);
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
            .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrado com id: " + dto.getCategoriaId()));
        entity.setCategoria(categoria);
        PlanoConta salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("PlanoConta não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
