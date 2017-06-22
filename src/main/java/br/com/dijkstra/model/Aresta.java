package br.com.dijkstra.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 
 * @author Fernando
 *
 */
public class Aresta implements Comparable<Aresta> {

	private Vertice origem;

	private Vertice destino;

	private BigDecimal peso;

	public Aresta(String origem, String destino, String peso) {
		this.origem = new Vertice(origem);
		this.destino = new Vertice(destino);
		this.peso = new BigDecimal(peso);
	}
	
	public Aresta(Vertice origem, Vertice destino) {
		this.origem = origem;
		this.destino = destino;
	}

	public Vertice getOrigem() {
		return origem;
	}

	public Vertice getDestino() {
		return destino;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	@Override
	public String toString() {
		return "origem -> " + origem + " , destino -> " + destino + " = " + peso + "\n";
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Aresta)) {
			return false;
		}
		Aresta castOther = (Aresta) other;
		return Objects.equals(origem, castOther.origem) && Objects.equals(destino, castOther.destino)
				&& Objects.equals(peso, castOther.peso);
	}

	@Override
	public int hashCode() {
		return Objects.hash(origem, destino, peso);
	}

	@Override
	public int compareTo(Aresta o) {
		return this.getPeso().compareTo(o.getPeso());
	}

}
