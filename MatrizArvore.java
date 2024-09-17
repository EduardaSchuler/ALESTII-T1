import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class MatrizArvore {
    private String[][] grid; // Matriz para armazenar os nós
    private int linhas;
    private int colunas;
    private List<Integer> somasParciais;

    public MatrizArvore(String arquivo) {
        somasParciais = new ArrayList<>();
        construirArvoreDoArquivo(arquivo);
    }

    public void construirArvoreDoArquivo(String nomeArquivo) {
        List<String> linhasArquivo = new ArrayList<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;

            // A primeira linha contém as dimensões da matriz
            if ((linha = leitor.readLine()) != null) {
                String[] dimensoes = linha.split("\\s+");
                linhas = Integer.parseInt(dimensoes[0]);
                colunas = Integer.parseInt(dimensoes[1]);
                grid = new String[linhas][colunas]; // Inicializar a matriz
            }

            // Leitura das linhas restantes
            while ((linha = leitor.readLine()) != null) {
                linhasArquivo.add(linha);
            }
        } catch (IOException erro) {
            System.err.println("Erro ao ler o arquivo: " + erro.getMessage());
        }

        // Preencher a matriz com base nas linhas lidas
        for (int i = 0; i < linhasArquivo.size(); i++) {
            String linhaAtual = linhasArquivo.get(i);
            for (int j = 0; j < linhaAtual.length() && j < colunas; j++) {
                grid[i][j] = String.valueOf(linhaAtual.charAt(j));
            }
        }

        // Processar a matriz para construir a árvore
        processarMatriz();
    }
    
    public void exibirMatriz() {
        for (String[] linha : grid) {
            for (String elemento : linha) {
                System.out.print(elemento);
            }
            System.out.println();
        }
    }

    private void processarMatriz() {
        boolean encontrouPrimeiro = false;

        // Busca o primeiro elemento não vazio na última linha
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = 0; j < grid[0].length; j++) {
                String elemento = grid[i][j];
                if (!elemento.trim().isEmpty() && !encontrouPrimeiro) {
                    encontrouPrimeiro = true;
                    System.out.println("Raiz encontrada: " + elemento);
                    explorarArvore(i, j, elemento, 0);
                }
            }
        }

        int maiorSoma = Collections.max(somasParciais);
        System.out.println("A maior soma encontrada é: " + maiorSoma);
    }

    private void explorarArvore(int linha, int coluna, String elemento, int somaAtual) {
        if (elemento.matches("\\d+")) {
            somaAtual += Integer.parseInt(elemento);
            elemento = "d"; // Marcamos que encontramos um número
        }

        switch (elemento) {
            case "|":
                caminharParaCima(linha - 1, coluna, somaAtual);
                break;

            case "W":
                caminharParaCima(linha - 1, coluna, somaAtual);
                caminharParaEsquerda(linha - 1, coluna - 1, somaAtual);
                caminharParaDireita(linha - 1, coluna + 1, somaAtual);
                break;

            case "V":
                caminharParaEsquerda(linha - 1, coluna - 1, somaAtual);
                caminharParaDireita(linha - 1, coluna + 1, somaAtual);
                break;

            case "#":
                somasParciais.add(somaAtual);
                break;

            default:
                break;
        }
    }

    private void caminharParaCima(int linha, int coluna, int somaAtual) {
        while (linha >= 0 && !grid[linha][coluna].matches("[VW#]")) {
            String elemento = grid[linha][coluna];
            if (elemento.matches("\\d+")) {
                somaAtual += Integer.parseInt(elemento);
            }
            linha--;
        }
        explorarArvore(linha, coluna, grid[linha][coluna], somaAtual);
    }

    private void caminharParaEsquerda(int linha, int coluna, int somaAtual) {
        while (linha >= 0 && coluna >= 0 && !grid[linha][coluna].matches("[VW#]")) {
            String elemento = grid[linha][coluna];
            if (elemento.matches("\\d+")) {
                somaAtual += Integer.parseInt(elemento);
            }
            linha--;
            coluna--;
        }
        explorarArvore(linha, coluna, grid[linha][coluna], somaAtual);
    }

    private void caminharParaDireita(int linha, int coluna, int somaAtual) {
        while (linha >= 0 && coluna < colunas && !grid[linha][coluna].matches("[VW#]")) {
            String elemento = grid[linha][coluna];
            if (elemento.matches("\\d+")) {
                somaAtual += Integer.parseInt(elemento);
            }
            linha--;
            coluna++;
        }
        explorarArvore(linha, coluna, grid[linha][coluna], somaAtual);
    }
}
