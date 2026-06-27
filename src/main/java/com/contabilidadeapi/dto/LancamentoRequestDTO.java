package com.contabilidadeapi.dto;

import jakarta.validation.constraints.*;

public class LancamentoRequestDTO {

    @NotNull(message = "CategoriaId é obrigatório")
    @Positive(message = "CategoriaId deve ser um ID válido (positivo)")
    private Long categoriaId;
    @NotNull(message = "CentroCustoId é obrigatório")
    @Positive(message = "CentroCustoId deve ser um ID válido (positivo)")
    private Long centroCustoId;
    @NotNull(message = "data lancamento não pode ser nulo")
    private java.time.LocalDateTime dataLancamento;
    @NotBlank(message = "descricao não pode estar em branco")
    private String descricao;
    @DecimalMin(value = "0.0", message = "valor não pode ser negativo")
    @NotNull(message = "valor não pode ser nulo")
    private Double valor;
    @NotBlank(message = "tipo não pode estar em branco")
    private String tipo;
    @NotBlank(message = "status não pode estar em branco")
    private String status;
    @NotBlank(message = "comprovante não pode estar em branco")
    private String comprovante;

    public Long getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }
    public Long getCentroCustoId() { return centroCustoId; }
    public void setCentroCustoId(Long centroCustoId) { this.centroCustoId = centroCustoId; }
    public java.time.LocalDateTime getDataLancamento() { return dataLancamento; }
    public void setDataLancamento(java.time.LocalDateTime dataLancamento) { this.dataLancamento = dataLancamento; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getComprovante() { return comprovante; }
    public void setComprovante(String comprovante) { this.comprovante = comprovante; }
}
