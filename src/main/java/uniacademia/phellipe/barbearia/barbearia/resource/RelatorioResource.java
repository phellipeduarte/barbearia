package uniacademia.phellipe.barbearia.barbearia.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uniacademia.phellipe.barbearia.barbearia.service.RelatorioService;

import java.util.HashMap;

@RestController
@RequestMapping("relatorios")
@PreAuthorize("hasHole('ADMIN')")
public class RelatorioResource {

    @Autowired
    RelatorioService relatorioService;

    // mostra o faturamento do mês considerando cada agendamento
    @GetMapping("/faturamento/mes")
    public ResponseEntity<Double> faturamentoMensal(){
        return ResponseEntity.ok(relatorioService.faturamentoMensal());
    }

    // mostra quais serviços são mais vendidos
    @GetMapping("/servico/mes")
    public ResponseEntity<HashMap<String, Integer>> servicosVendidosPorQuantidade(){
        return ResponseEntity.ok(relatorioService.servicosVendidosPorQuantidade());
    }
}
