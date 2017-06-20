package br.com.dijkstra.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Representação de um Grafo
 * 
 * @author Fernando
 *
 */
public class Grafo {

	private Set<Vertice> vertices = new HashSet<>();
	private Set<Aresta> arestas = new HashSet<>();

	public void add(Aresta aresta) {
		this.arestas.add(aresta);
		this.vertices.add(aresta.getOrigem());
		this.vertices.add(aresta.getDestino());
	}

	@Override
	public String toString() {
		return "Arvore [" + arestas + "], size: " + arestas.size();
	}

	/**
	 * Seleciona um vertice de forma aleatória
	 * 
	 * @return
	 */
	public Vertice selecionarAleatorio() {
		List<Vertice> list = new ArrayList<>(vertices);
		Collections.shuffle(list);
		return list.get(new Random().nextInt(vertices.size() - 1));
	}

	/**
	 * Lista todos vertices adjacentes dos vertices já rotulados
	 * 
	 * @param vertices
	 * @return Vertices adjacentes ordenados por peso
	 */
	public List<Aresta> getAdjacentes(List<Vertice> rotulados) {
		List<Aresta> retorno = new ArrayList<>();
		for (Vertice vertice: rotulados) {
			List<Aresta> list = arestas.stream()
										.filter(aresta -> aresta.getOrigem().equals(vertice))
										.collect(Collectors.toList());
			retorno.addAll(list);
		}
		return retorno;
	}

	public List<Vertice> getVertices() {
		return new ArrayList<>(vertices);
	}

	public Integer getQtdArestas() {
		return arestas.size();
	}

	/**
	 * Inicializa peso dos vertices com um valor relativamente alto.
	 * 
	 */
	public void inicializar() {
		arestas.forEach(are -> {
			are.getOrigem().atribuirPesoInicial();
			are.getDestino().atribuirPesoInicial();
		});
	}

	public void atualizarPesoVertices(Vertice origem, List<Aresta> adjacentes) {
		adjacentes.forEach(aresta ->{
			BigDecimal pesoAtual = origem.getPeso().add(aresta.getPeso());
			Vertice destino = aresta.getDestino();
			if(destino.getPeso().compareTo(pesoAtual) == 1){
				aresta.getDestino().atualizarPeso(pesoAtual);
				atualizarPeso(aresta.getOrigem(), origem.getPeso());
			}
		});
		
	}

	public List<Vertice> getAdjacentes(Vertice vertice) {
		return arestas.stream()
				.filter(aresta -> aresta.getOrigem().equals(vertice))
				.map(ares -> ares.getDestino())
				.collect(Collectors.toList());
	}

	public void atualizarPeso(Vertice origem, BigDecimal peso) {
		arestas.stream()
			.filter(aresta -> aresta.getOrigem().equals(origem))
			.forEach(aresta -> {
				aresta.getOrigem().atualizarPeso(peso);
			});
	}

	public Vertice buscar(String idOrigem) {
		return getVertices().get(getVertices().indexOf(new Vertice(idOrigem)));
	}


}
