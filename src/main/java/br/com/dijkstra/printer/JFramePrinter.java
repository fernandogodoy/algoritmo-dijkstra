package br.com.dijkstra.printer;

import java.awt.Dimension;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JApplet;
import javax.swing.JFrame;

import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;

import br.com.dijkstra.model.Aresta;
import br.com.dijkstra.model.Vertice;

public class JFramePrinter extends JApplet implements Printer {

	private static final long serialVersionUID = 1L;

	private JGraphXAdapter<Vertice, DefaultEdge> jgAdapter;
	private static final Dimension DEFAULT_SIZE = new Dimension(500, 400);

	private List<Vertice> vertices;
	private Vertice destino;

	private JFrame frame = new JFrame();
	private ListenableGraph<Vertice, DefaultEdge> grafo = new DefaultListenableGraph<>(
			new DefaultDirectedGraph<>(DefaultEdge.class));

	public JFramePrinter(List<Vertice> vertices, Vertice destino) {
		this.init();
		this.vertices = vertices;
		this.destino = destino;
	}

	@Override
	public void init() {
		jgAdapter = new JGraphXAdapter<>(grafo);
		getContentPane().add(new mxGraphComponent(jgAdapter));
		resize(DEFAULT_SIZE);
	}

	@Override
	public void print() {

		List<Aresta> arestas = new ArrayList<>();
		Vertice vertice = vertices.get(vertices.indexOf(destino));
		grafo.addVertex(vertice);
		
		while (vertice.getAnterior() != null) {
			arestas.add(new Aresta(vertice.getAnterior(), vertice));
			vertice = vertice.getAnterior();
			grafo.addVertex(vertice);
		}

		for (Aresta aresta : arestas) {
			grafo.addEdge(aresta.getOrigem(), aresta.getDestino());
		}
		mxCircleLayout layout = new mxCircleLayout(jgAdapter);
		layout.execute(jgAdapter.getDefaultParent());

		jframeConfig();

	}

	private void jframeConfig() {
		frame.getContentPane().add(this);
		frame.setTitle("Caminho Mínimo - Algoritmo de Dijkstra");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

}
