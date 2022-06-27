package uniacademia.phellipe.barbearia.barbearia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uniacademia.phellipe.barbearia.barbearia.model.Agendamento;

import java.util.HashMap;
import java.util.List;

@Service
public class RelatorioService {

    @Autowired
    AgendamentoService agendamentoService;

    public Double faturamentoMensal(){
        List<Agendamento> listaAgendamentosNoMes = agendamentoService.listarAgendamentosNoMes();
        Double[] precoPorServico = new Double[]{35.0, 15.0, 50.0, 15.0, 20.0, 40.0, 60.0};
        Double valorTotal = 0.0;

        for(Agendamento agendamento: listaAgendamentosNoMes){
            valorTotal = valorTotal + precoPorServico[agendamento.getTipoCorte().ordinal()];
        }

        return valorTotal;
    }

    public HashMap<String, Integer> servicosVendidosPorQuantidade() {
        List<Agendamento> listaAgendamentosNoMes = agendamentoService.listarAgendamentosNoMes();
        HashMap<String, Integer> quantidadePorServico = new HashMap<String, Integer>();
        Integer[] quantidades = new Integer[]{0, 0, 0, 0, 0, 0, 0};
        String[] listaTipoCorte = new String[]{"Corte", "Barba", "CorteBarba", "Desenho", "Luzes", "Alisamento", "Pintura"};

        for(Agendamento agendamento: listaAgendamentosNoMes){
            quantidades[agendamento.getTipoCorte().ordinal()]++;
        }

        for(int i = 0; i < 7; i++){
            quantidadePorServico.put(listaTipoCorte[i], quantidades[i]);
        }

        return quantidadePorServico;
    }
}
