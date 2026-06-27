package com.contabilidadeapi.service;

import com.contabilidadeapi.dto.CategoriaRequestDTO;
import com.contabilidadeapi.dto.CategoriaResponseDTO;
import com.contabilidadeapi.exception.ResourceNotFoundException;
import com.contabilidadeapi.mapper.CategoriaMapper;
import com.contabilidadeapi.model.Categoria;
import com.contabilidadeapi.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Autowired
    private CategoriaMapper mapper;

    @Transactional(readOnly = true)
    public List<CategoriaResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoriaResponseDTO buscar(Long id) {
        Categoria entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public CategoriaResponseDTO criar(CategoriaRequestDTO dto) {
        Categoria entity = mapper.toEntity(dto);
        Categoria salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Categoria não encontrado com id: " + id);
        }
        Categoria entity = mapper.toEntity(dto);
        entity.setId(id);
        Categoria salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Categoria não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
