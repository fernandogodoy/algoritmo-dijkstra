package br.com.dijkstra.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 
 * @author Fernando
 *
 */
public class Vertice {

	private String id;

	private BigDecimal peso;

	public Vertice(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Vertice)) {
			return false;
		}
		Vertice castOther = (Vertice) other;
		return Objects.equals(id, castOther.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return id;
	}

	/**
	 * Atribui {@link Integer#MAX_VALUE} para cada vertice
	 * 
	 */
	public void atribuirPesoInicial() {
		this.peso = BigDecimal.valueOf(Integer.MAX_VALUE);
	}

	/**
	 * Atualizar valor do peso
	 * 
	 * @param peso
	 */
	public void atualizarPeso(BigDecimal peso) {
		this.peso = peso;
	}

}
