package com.contabilidadeapi.mapper;

import com.contabilidadeapi.dto.CategoriaRequestDTO;
import com.contabilidadeapi.dto.CategoriaResponseDTO;
import com.contabilidadeapi.model.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    Categoria toEntity(CategoriaRequestDTO dto);

    CategoriaResponseDTO toResponseDTO(Categoria entity);
}
