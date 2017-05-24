package br.com.dijkstra;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.dijkstra.model.Aresta;
import br.com.dijkstra.model.Grafo;
import br.com.dijkstra.model.Vertice;
import br.com.dijkstra.util.FileUtil;

/**
 * 
 * @author Fernando
 *
 */
public class Dijkstra {

	private List<Vertice> rotulados = new ArrayList<>();
	private List<Vertice> naoRotulados = new ArrayList<>();

	public static void main(String[] args) {
		Dijkstra dijkstra = new Dijkstra();
		dijkstra.execute("1", "9");
		System.out.println(dijkstra.rotulados);
		
	}

	private void execute(String idOrigem, String idDestino) {
		Grafo grafo = FileUtil.readFile();
		grafo.inicializar();
		adicionarNaoRotulados(grafo);
		Vertice origem = grafo.getVertices().get(grafo.getVertices().indexOf(new Vertice(idOrigem)));
		grafo.atualizarPeso(origem, BigDecimal.ZERO);
		rotular(origem, grafo);
		
		while(!naoRotulados.isEmpty()){
			List<Aresta> adjacentes = grafo.getAdjacentes(rotulados);
			grafo.atualizarPesoVertices(origem, adjacentes);
			Aresta arestaMenorCusto = selecionarMenorCusto(adjacentes);
			rotular(arestaMenorCusto.getDestino(), grafo);
			origem = arestaMenorCusto.getDestino();
		}
	}

	private void rotular(Vertice vertice, Grafo grafo) {
		rotulados.add(vertice);
		naoRotulados.remove(vertice);
	}

	private Aresta selecionarMenorCusto(List<Aresta> adjacentes) {
		Aresta selecionada = null;
		BigDecimal peso = BigDecimal.valueOf(Long.MAX_VALUE);
		for (Aresta aresta : adjacentes) {
			if (aresta.getDestino().getPeso().compareTo(peso) == -1 && naoRotulados.contains(aresta.getDestino())) {
				selecionada = aresta;
				peso = aresta.getDestino().getPeso();
			}
		}
		return selecionada;
	}

	private void adicionarNaoRotulados(Grafo grafo) {
		naoRotulados.addAll(grafo.getVertices());
	}

}
