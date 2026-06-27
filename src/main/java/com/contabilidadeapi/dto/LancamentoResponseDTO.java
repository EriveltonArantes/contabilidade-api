package com.contabilidadeapi.dto;

public class LancamentoResponseDTO {

    private Long id;
    private Long categoriaId;
    private Long centroCustoId;
    private java.time.LocalDateTime dataLancamento;
    private String descricao;
    private Double valor;
    private String tipo;
    private String status;
    private String comprovante;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
