package br.com.dijkstra.printer;

import java.util.List;

import br.com.dijkstra.model.Vertice;

/**
 * 
 * @author Fernando
 *
 */
public class ConsolePrinter implements Printer {
	
	private List<Vertice> vertices;

	public ConsolePrinter(List<Vertice> vertices) {
		this.vertices = vertices;
	}

	@Override
	public void print() {
		System.out.println("R = " + vertices);
		System.out.println("Z = " + vertices.stream().reduce((first, second) -> second).get().getPeso());
	}

	

}
