package com.contabilidadeapi.mapper;

import com.contabilidadeapi.dto.PlanoContaRequestDTO;
import com.contabilidadeapi.dto.PlanoContaResponseDTO;
import com.contabilidadeapi.model.PlanoConta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlanoContaMapper {

    @Mapping(target = "categoria", ignore = true)
    PlanoConta toEntity(PlanoContaRequestDTO dto);

    @Mapping(target = "categoriaId", source = "categoria.id")
    PlanoContaResponseDTO toResponseDTO(PlanoConta entity);
}
