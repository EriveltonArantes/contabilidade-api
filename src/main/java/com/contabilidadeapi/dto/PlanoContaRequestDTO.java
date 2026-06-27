package com.contabilidadeapi.dto;

import jakarta.validation.constraints.*;

public class PlanoContaRequestDTO {

    @NotNull(message = "CategoriaId é obrigatório")
    @Positive(message = "CategoriaId deve ser um ID válido (positivo)")
    private Long categoriaId;
    @NotBlank(message = "codigo não pode estar em branco")
    private String codigo;
    @NotBlank(message = "nome não pode estar em branco")
    private String nome;
    @NotBlank(message = "tipo não pode estar em branco")
    private String tipo;
    @NotNull(message = "nivel não pode ser nulo")
    private Integer nivel;
    @NotNull(message = "ativo não pode ser nulo")
    private Boolean ativo;

    public Long getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public Integer getNivel() { return nivel; }
    public void setNivel(Integer nivel) { this.nivel = nivel; }
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}
