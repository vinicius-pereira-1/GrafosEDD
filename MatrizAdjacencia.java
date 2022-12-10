import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class MatrizAdjacencia {
    private int[][] grafo;
    private Map<String, Integer> vertices;

    public MatrizAdjacencia(int[][] grafo, String vertices[]) {
        this.grafo = grafo;
        this.vertices = new HashMap<>();

        for (int i = 0; i < vertices.length; i++) {
            this.vertices.put(vertices[i], i);
        }
    }
    
    public String obtemVerticeMaiorGrau() {
        String vertice = null;
        int maior = 0;

        for (String rotulo : vertices.keySet()) {
            int grau = obtemGrau(rotulo);

            if (grau > maior) {
                vertice = rotulo;
                maior = grau;
            }
        }

        return vertice;
    }

    public String obtemVerticeMenorGrau() {
        String Menorvertice = null;

        int grauInicial = 0;
        int cont = 1;

        for (String rotulo : vertices.keySet()) {

            int grau = obtemGrau(rotulo);

            if (cont == 1) {
                grauInicial = obtemGrau(rotulo);
            }

            if (grau < grauInicial) {
                Menorvertice = rotulo;
            }
            cont++;
        }
        return Menorvertice;

    }

    public List<String> obtemVizinhos(String vi) {
        int i = vertices.get(vi);
        List<String> vizinhos = new ArrayList<>();

        for (String vj : vertices.keySet()) {
            int j = vertices.get(vj);

            if (grafo[i][j] > 0) {
                vizinhos.add(vj);
            }
        }

        return vizinhos;
    }

    public void buscaEmProfundidadeRecursiva() {
        String vertice = vertices.keySet().iterator().next();
        List<String> visitados = new ArrayList<>();

        System.out.print("Busca em profundidade recursiva: [ ");

        this.buscaEmProfundidade(vertice, visitados);

        System.out.println("]");
    }

    public void buscaEmProfundidade(String vertice, List<String> visitados) {
        if (!visitados.contains(vertice)) {
            System.out.print(vertice + " ");
            visitados.add(vertice);

            List<String> vizinhos = obtemVizinhos(vertice);

            for (String vizinho : vizinhos) {
                buscaEmProfundidade(vizinho, visitados);
            }
        }
    }

    public void buscaEmProfundidadeIterativa() {
        String vertice = vertices.keySet().iterator().next();
        Stack<String> naoVisitados = new Stack<>();
        List<String> visitados = new ArrayList<>();

        naoVisitados.push(vertice);

        System.out.print("Busca em profundidade iterativa: [ ");

        while (!naoVisitados.isEmpty()) {
            String vi = naoVisitados.pop();

            if (!visitados.contains(vi)) {
                visitados.add(vi);
                System.out.print(vi + " ");

                List<String> vizinhos = obtemVizinhos(vi);
                Collections.reverse(vizinhos);

                vizinhos.forEach(vj -> naoVisitados.push(vj));
            }
        }

        System.out.println("]");
    }

    public int obtemGrau(String vertice) {
        int i = vertices.get(vertice);
        int grau = 0;

        for (int j = 0; j < grafo.length; j++) {
            if (grafo[i][j] > 0 || grafo[j][i] > 0) {
                grau++;
            }
        }

        return grau;
    }

    public static void main(String args[]) {
        int grafo[][] = {
                { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0 },
                { 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
                { 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
                { 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
                { 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0 },
                { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 },
                { 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
                { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
                { 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
        };

        String vertices[] = { "AC", "AL", "AP", "AM", "BA", "CE", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR",
                "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", "DF" };

        MatrizAdjacencia matriz = new MatrizAdjacencia(grafo, vertices);

        String verticeMaior = matriz.obtemVerticeMaiorGrau();
        String verticeMenor = matriz.obtemVerticeMenorGrau();

        
        System.out.println("------------------");
        
        System.out.println("Estado com  maior número de estados vizinhos: " + verticeMaior);

        System.out.println("------------------");

        System.out.println("Estado com  menor número de estados vizinhos: " + verticeMenor);

        System.out.println("------------------");

        matriz.buscaEmProfundidadeIterativa();
        
        System.out.println("------------------");

        matriz.buscaEmProfundidadeRecursiva();
    }
}

