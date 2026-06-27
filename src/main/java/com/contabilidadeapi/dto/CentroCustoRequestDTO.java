package com.contabilidadeapi.dto;

import jakarta.validation.constraints.*;

public class CentroCustoRequestDTO {

    @NotBlank(message = "nome não pode estar em branco")
    private String nome;
    @NotBlank(message = "codigo não pode estar em branco")
    private String codigo;
    @NotBlank(message = "descricao não pode estar em branco")
    private String descricao;
    @NotBlank(message = "responsavel não pode estar em branco")
    private String responsavel;
    @NotNull(message = "ativo não pode ser nulo")
    private Boolean ativo;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getResponsavel() { return responsavel; }
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}
