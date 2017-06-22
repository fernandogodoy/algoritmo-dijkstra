# Algoritmo de Dijkstra

Esta aplicação possui a implementação do Algoritmo de Dijkstra para geração do caminho mínimo, fazendo uso da biblioteca: http://jgrapht.org/ para gerar uma representação gráfica do resultado.

1) Como resultado da execução, terá a escrita no console onde é apresentado: 

 > Vertices Percorridos 
 
 > Custo total do caminho.
 
 > Ex: R= [1, 3, 2, 5, 4, 8, 7, 6, 9] Z = 24
 

2) Representação gráfica do Caminho Mínimo.

<html>
	<img src="https://github.com/fernandogodoy/algoritmo-dijkstra/blob/master/print.PNG"/>

</html>

# Arquivo de configuração:
O caminho mínimo é gerado com base no arquivo: https://github.com/fernandogodoy/algoritmo-dijkstra/blob/master/src/main/resources/listaAdjacencia

Este arquivo deve conter representação da lista de adjacência, onde cada linha representa um nó contendo: 
 > Origem (->) Destino (-) Peso

 > Ex: 8 -> 6-6
