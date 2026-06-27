package com.contabilidadeapi.service;

import com.contabilidadeapi.dto.LancamentoRequestDTO;
import com.contabilidadeapi.dto.LancamentoResponseDTO;
import com.contabilidadeapi.exception.ResourceNotFoundException;
import com.contabilidadeapi.mapper.LancamentoMapper;
import com.contabilidadeapi.model.Lancamento;
import com.contabilidadeapi.repository.LancamentoRepository;
import com.contabilidadeapi.repository.CategoriaRepository;
import com.contabilidadeapi.model.Categoria;
import com.contabilidadeapi.repository.CentroCustoRepository;
import com.contabilidadeapi.model.CentroCusto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LancamentoService {

    @Autowired
    private LancamentoRepository repository;

    @Autowired
    private LancamentoMapper mapper;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CentroCustoRepository centroCustoRepository;

    @Transactional(readOnly = true)
    public List<LancamentoResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LancamentoResponseDTO buscar(Long id) {
        Lancamento entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Lancamento não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public LancamentoResponseDTO criar(LancamentoRequestDTO dto) {
        Lancamento entity = mapper.toEntity(dto);
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
            .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrado com id: " + dto.getCategoriaId()));
        entity.setCategoria(categoria);
        CentroCusto centroCusto = centroCustoRepository.findById(dto.getCentroCustoId())
            .orElseThrow(() -> new ResourceNotFoundException("CentroCusto não encontrado com id: " + dto.getCentroCustoId()));
        entity.setCentroCusto(centroCusto);
        Lancamento salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public LancamentoResponseDTO atualizar(Long id, LancamentoRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Lancamento não encontrado com id: " + id);
        }
        Lancamento entity = mapper.toEntity(dto);
        entity.setId(id);
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
            .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrado com id: " + dto.getCategoriaId()));
        entity.setCategoria(categoria);
        CentroCusto centroCusto = centroCustoRepository.findById(dto.getCentroCustoId())
            .orElseThrow(() -> new ResourceNotFoundException("CentroCusto não encontrado com id: " + dto.getCentroCustoId()));
        entity.setCentroCusto(centroCusto);
        Lancamento salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Lancamento não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
