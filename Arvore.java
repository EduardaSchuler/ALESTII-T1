import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Arvore {
    private String[][] matriz; // Matriz para armazenar os nós
    private int numLinhas;
    private int numColunas;
    private List<Integer> somas;

    public Arvore(String arquivo) {
        somas = new ArrayList<>();
        construirArvoreApartirDoArquivo(arquivo);
    }

    public void construirArvoreApartirDoArquivo(String nomeArquivo) {
        List<String> linhas = new ArrayList<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;

            // Primeira linha contém o tamanho da matriz
            if ((linha = leitor.readLine()) != null) {
                String[] dimensoes = linha.split("\\s+");
                numLinhas = Integer.parseInt(dimensoes[0]);
                numColunas = Integer.parseInt(dimensoes[1]);
                matriz = new String[numLinhas][numColunas]; // Inicializar a matriz
            }

            // Mapear os nós para as posições na matriz
            while ((linha = leitor.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException erro) {
            System.err.println("Erro ao ler o arquivo: " + erro.getMessage());
            System.out.println("ARQUIVO NÃO ENCONTRADO!");
        }

        // Preencher a matriz com base nas posições da linha
        for (int linha = 0; linha < linhas.size(); linha++) {
            String linhaAtual = linhas.get(linha);
            for (int coluna = 0; coluna < linhaAtual.length() && coluna < numColunas; coluna++) {
                matriz[linha][coluna] = String.valueOf(linhaAtual.charAt(coluna));
            }
        }

        // Cria os nodos a partir da matriz
        construirArvoreApartirDaMatriz();
    }
    
    public void imprimirMatriz() {
        for (String[] linha : matriz) {
            for (String elemento : linha) {
                System.out.print(elemento);
            }
            System.out.println();
        }
    }

    private void construirArvoreApartirDaMatriz() {
        boolean encontrouElemento = false;

        // Percorre a matriz da última linha e primeira coluna em busca de um elemento
        for (int i = matriz.length - 1; i >= 0; i--) {
            for (int j = 0; j < matriz[0].length; j++) {
                String elemento = matriz[i][j];
                if (!elemento.trim().isEmpty() && !encontrouElemento) {
                    encontrouElemento = true;
                    System.out.println("Raiz: " + elemento);
                    percorrerArvore(i, j, elemento, 0);
                }
            }
        }

        int maiorSoma = Collections.max(somas);
        System.out.println("A maior soma parcial é: " + maiorSoma);
    }

    private void percorrerArvore(int linha, int coluna, String elemento, int somaParcial) {
        if (elemento.matches("\\d+")) {
            somaParcial += Integer.parseInt(elemento);
            elemento = "d"; // Indica que encontramos um número
        }

        switch (elemento) {
            case "|":
                percorrerParaCima(linha - 1, coluna, somaParcial);
                break;

            case "W":
                percorrerParaCima(linha - 1, coluna, somaParcial);
                percorrerParaEsquerda(linha - 1, coluna - 1, somaParcial);
                percorrerParaDireita(linha - 1, coluna + 1, somaParcial);
                break;

            case "V":
                percorrerParaEsquerda(linha - 1, coluna - 1, somaParcial);
                percorrerParaDireita(linha - 1, coluna + 1, somaParcial);
                break;

            case "#":
                somas.add(somaParcial);
                break;

            default:
                break;
        }
    }

    private void percorrerParaCima(int linha, int coluna, int somaParcial) {
        while (linha >= 0 && !matriz[linha][coluna].matches("[VW#]")) {
            String elemento = matriz[linha][coluna];
            if (elemento.matches("\\d+")) {
                somaParcial += Integer.parseInt(elemento);
            }
            linha--;
        }
        percorrerArvore(linha, coluna, matriz[linha][coluna], somaParcial);
    }

    private void percorrerParaEsquerda(int linha, int coluna, int somaParcial) {
        while (linha >= 0 && coluna >= 0 && !matriz[linha][coluna].matches("[VW#]")) {
            String elemento = matriz[linha][coluna];
            if (elemento.matches("\\d+")) {
                somaParcial += Integer.parseInt(elemento);
            }
            linha--;
            coluna--;
        }
        percorrerArvore(linha, coluna, matriz[linha][coluna], somaParcial);
    }

    private void percorrerParaDireita(int linha, int coluna, int somaParcial) {
        while (linha >= 0 && coluna < numColunas && !matriz[linha][coluna].matches("[VW#]")) {
            String elemento = matriz[linha][coluna];
            if (elemento.matches("\\d+")) {
                somaParcial += Integer.parseInt(elemento);
            }
            linha--;
            coluna++;
        }
        percorrerArvore(linha, coluna, matriz[linha][coluna], somaParcial);
    }
}
