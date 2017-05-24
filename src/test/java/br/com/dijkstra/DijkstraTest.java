package br.com.dijkstra;

import org.junit.Assert;
import org.junit.Test;

import br.com.dijkstra.model.Grafo;
import br.com.dijkstra.util.FileUtil;

/**
 * 
 * @author Fernando
 *
 */
public class DijkstraTest {

	@Test
	public void montaListaTest() {
		Grafo grafo = FileUtil.readFile();
		Assert.assertNotNull(grafo);
		Assert.assertEquals(13, grafo.getQtdArestas().intValue());
	}
}
