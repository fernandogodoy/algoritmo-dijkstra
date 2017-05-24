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

	private Boolean isAberto;

	public Vertice(String id) {
		this.id = id;
		this.isAberto = Boolean.TRUE;
	}

	public String getId() {
		return id;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public Boolean isAberto() {
		return isAberto;
	}

	public void fechar() {
		this.isAberto = Boolean.FALSE;
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
		return id + "-" + peso;
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
