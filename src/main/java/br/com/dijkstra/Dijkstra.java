package br.com.dijkstra;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.dijkstra.model.Aresta;
import br.com.dijkstra.model.Grafo;
import br.com.dijkstra.model.Vertice;
import br.com.dijkstra.printer.ConsolePrinter;
import br.com.dijkstra.printer.Printer;
import br.com.dijkstra.util.FileUtil;

/**
 * 
 * @author Fernando
 *
 */
public class Dijkstra {

	private List<Vertice> rotulados = new ArrayList<>();
	private List<Vertice> naoRotulados = new ArrayList<>();

	private Printer printer;

	public static void main(String[] args) {
		Dijkstra dijkstra = new Dijkstra();
		dijkstra.execute("1", "9");
		dijkstra.printConsole();
	}

	private void printConsole() {
		printer = new ConsolePrinter(rotulados);
		printer.print();
	}

	private void execute(String idOrigem, String idDestino) {
		Grafo grafo = FileUtil.readFile();
		Vertice destino = new Vertice(idDestino);
		
		grafo.inicializar();
		adicionarNaoRotulados(grafo);
		Vertice origem = grafo.buscar(idOrigem);
		grafo.atualizarPeso(origem, BigDecimal.ZERO);
		rotular(origem);

		while (!naoRotulados.isEmpty()) {
			List<Aresta> adjacentes = grafo.getAdjacentes(rotulados);
			grafo.atualizarPesoVertices(origem, adjacentes);
			Aresta arestaMenorCusto = selecionarMenorCusto(adjacentes);
			rotular(arestaMenorCusto.getDestino());
			origem = arestaMenorCusto.getDestino();
			
			if(origem.equals(destino)) break;
		}
	}

	/**
	 * Rotula o vertice selecionado
	 * 
	 * @param vertice
	 */
	private void rotular(Vertice vertice) {
		rotulados.add(vertice);
		naoRotulados.remove(vertice);
	}

	/**
	 * Seleciona o vertice não rotulado de menor custo
	 * 
	 * @param adjacentes
	 * @return
	 */
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
