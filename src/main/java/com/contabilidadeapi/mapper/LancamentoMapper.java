package com.contabilidadeapi.mapper;

import com.contabilidadeapi.dto.LancamentoRequestDTO;
import com.contabilidadeapi.dto.LancamentoResponseDTO;
import com.contabilidadeapi.model.Lancamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LancamentoMapper {

    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "centroCusto", ignore = true)
    Lancamento toEntity(LancamentoRequestDTO dto);

    @Mapping(target = "categoriaId", source = "categoria.id")
    @Mapping(target = "centroCustoId", source = "centroCusto.id")
    LancamentoResponseDTO toResponseDTO(Lancamento entity);
}
