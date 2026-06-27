package com.contabilidadeapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lancamentos")
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name = "centroCusto_id")
    private CentroCusto centroCusto;
    private java.time.LocalDateTime dataLancamento;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;
    private Double valor;
    @Column(nullable = false)
    private String tipo;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private String comprovante;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    public CentroCusto getCentroCusto() { return centroCusto; }
    public void setCentroCusto(CentroCusto centroCusto) { this.centroCusto = centroCusto; }
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
