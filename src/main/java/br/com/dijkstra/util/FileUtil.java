package br.com.dijkstra.util;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.dijkstra.model.Aresta;
import br.com.dijkstra.model.Grafo;

public class FileUtil {

	private static final String EMPTY = "";
	private static final Pattern REGEX = Pattern.compile("(.*) -> (.*)", Pattern.DOTALL);
	private static final Pattern REGEX_VERTEX = Pattern.compile("(.*)-(.*)", Pattern.UNIX_LINES);

	public static Grafo readFile() {
		Path path = getPath();
		Grafo arvore = new Grafo();
		try (Scanner scanner = new Scanner(path.toFile())) {
			while (scanner.hasNext()) {
				Matcher matcher = REGEX.matcher(scanner.nextLine().trim());
				if (matcher.find()) {
					String idVerticeOrigem = matcher.group(1);
					String replaced = matcher.group(2).trim().replace(",", System.lineSeparator());
					Matcher matcherVertex = REGEX_VERTEX.matcher(replaced.toString());
					while (matcherVertex.find()) {
						String idVerticeDestino = matcherVertex.group(1).trim();
						String peso = matcherVertex.group(2).replaceAll(System.lineSeparator(), EMPTY).trim();
						Aresta aresta = new Aresta(idVerticeOrigem, idVerticeDestino, peso);
						arvore.add(aresta);
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return arvore;
	}

	private static Path getPath() {
		try {
			return Paths.get(ClassLoader.getSystemResource("listaAdjacencia").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
}
