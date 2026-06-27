package com.contabilidadeapi.mapper;

import com.contabilidadeapi.dto.CentroCustoRequestDTO;
import com.contabilidadeapi.dto.CentroCustoResponseDTO;
import com.contabilidadeapi.model.CentroCusto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CentroCustoMapper {

    CentroCusto toEntity(CentroCustoRequestDTO dto);

    CentroCustoResponseDTO toResponseDTO(CentroCusto entity);
}
