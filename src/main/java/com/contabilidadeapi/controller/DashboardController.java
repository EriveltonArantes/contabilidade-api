package com.contabilidadeapi.controller;

import com.contabilidadeapi.model.Categoria;
import com.contabilidadeapi.model.CentroCusto;
import com.contabilidadeapi.model.Lancamento;
import com.contabilidadeapi.model.PlanoConta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@Tag(name = "Dashboard", description = "KPIs e totais do sistema")
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private com.contabilidadeapi.repository.CategoriaRepository categoriaRepository;

    @Autowired
    private com.contabilidadeapi.repository.CentroCustoRepository centroCustoRepository;

    @Autowired
    private com.contabilidadeapi.repository.LancamentoRepository lancamentoRepository;

    @Autowired
    private com.contabilidadeapi.repository.PlanoContaRepository planoContaRepository;

    @Operation(summary = "Resumo com totais, somas e gráficos de status")
    @Transactional(readOnly = true)
    @GetMapping("/resumo")
    public Map<String, Object> resumo() {
        Map<String, Object> resumo = new LinkedHashMap<>();
        resumo.put("totalCategoria", categoriaRepository.count());
        resumo.put("totalCentroCusto", centroCustoRepository.count());
        resumo.put("totalLancamento", lancamentoRepository.count());
        resumo.put("somaValorLancamento", lancamentoRepository.findAll().stream().filter(e -> e.getValor() != null).mapToDouble(e -> e.getValor()).sum());
        resumo.put("graficoLancamento", lancamentoRepository.findAll().stream()
            .collect(java.util.stream.Collectors.groupingBy(
                item -> String.valueOf(item.getStatus()),
                java.util.LinkedHashMap::new,
                java.util.stream.Collectors.counting())));
        resumo.put("totalPlanoConta", planoContaRepository.count());
        resumo.put("somaNivelPlanoConta", planoContaRepository.findAll().stream().filter(e -> e.getNivel() != null).mapToInt(e -> e.getNivel()).sum());
        return resumo;
    }
}
