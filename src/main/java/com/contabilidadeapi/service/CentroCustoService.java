package com.contabilidadeapi.service;

import com.contabilidadeapi.dto.CentroCustoRequestDTO;
import com.contabilidadeapi.dto.CentroCustoResponseDTO;
import com.contabilidadeapi.exception.ResourceNotFoundException;
import com.contabilidadeapi.mapper.CentroCustoMapper;
import com.contabilidadeapi.model.CentroCusto;
import com.contabilidadeapi.repository.CentroCustoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CentroCustoService {

    @Autowired
    private CentroCustoRepository repository;

    @Autowired
    private CentroCustoMapper mapper;

    @Transactional(readOnly = true)
    public List<CentroCustoResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CentroCustoResponseDTO buscar(Long id) {
        CentroCusto entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("CentroCusto não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public CentroCustoResponseDTO criar(CentroCustoRequestDTO dto) {
        CentroCusto entity = mapper.toEntity(dto);
        CentroCusto salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public CentroCustoResponseDTO atualizar(Long id, CentroCustoRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("CentroCusto não encontrado com id: " + id);
        }
        CentroCusto entity = mapper.toEntity(dto);
        entity.setId(id);
        CentroCusto salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("CentroCusto não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
