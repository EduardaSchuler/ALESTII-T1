import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ManipulaMatriz {
    private char[][] matriz;
    private Set<String> visitados;

    public ManipulaMatriz() {
        pegarMatriz();
        visitados = new HashSet<>();
    }

    private void pegarMatriz() {
        matriz = Reader.readFile("casof30.txt");
    }

    public String encontrarCaminhoComMaiorSoma() {
        List<String> caminhos = new ArrayList<>();
        List<Integer> somas = new ArrayList<>();
        
        char valorRaiz = acharValorRaiz();
        if (valorRaiz == ' ') {
            System.out.println("Raiz não encontrada.");
            return "Raiz não encontrada.";
        }

        int[] posicaoRaiz = acharIndiceRaiz();
        percorrerRamo(posicaoRaiz[0], posicaoRaiz[1], caminhos, somas, new StringBuilder(), 0);

        // Encontrar o caminho com maior soma
        int maxSoma = Integer.MIN_VALUE;
        String melhorCaminho = "";
        for (int i = 0; i < somas.size(); i++) {
            if (somas.get(i) > maxSoma) {
                maxSoma = somas.get(i);
                melhorCaminho = caminhos.get(i);
            }
        }

        return melhorCaminho + " com a soma: " + maxSoma;
    }

    private void percorrerRamo(int linha, int coluna, List<String> caminhos, List<Integer> somas, StringBuilder caminhoAtual, int somaAtual) {
        if (linha < 0 || coluna < 0 || linha >= matriz.length || coluna >= matriz[0].length) {
            return;  // Fora dos limites da matriz
        }

        char valorAtual = matriz[linha][coluna];
        caminhoAtual.append(valorAtual);

        // Se for um número, adicionar ao total
        if (Character.isDigit(valorAtual)) {
            somaAtual += Character.getNumericValue(valorAtual);
        }

        // Marcar posição como visitada
        String posicao = linha + "," + coluna;
        if (visitados.contains(posicao)) {
            return;  // Evitar loops e duplicações
        }
        visitados.add(posicao);

        // Se encontrar o traço de finalização '#', adicione o caminho e a soma e pare
        if (valorAtual == '#') {
            caminhos.add(caminhoAtual.toString());
            somas.add(somaAtual);
            return;
        }

        // Decisão com base no valor atual (bifurcação, trifurcação, etc.)
        switch (valorAtual) {
            case 'V':  // Bifurcação
                percorrerRamo(linha - 1, coluna - 1, caminhos, somas, new StringBuilder(caminhoAtual), somaAtual); // Esquerda
                percorrerRamo(linha - 1, coluna + 1, caminhos, somas, new StringBuilder(caminhoAtual), somaAtual); // Direita
                break;
            case 'W':  // Trifurcação
                percorrerRamo(linha - 1, coluna - 1, caminhos, somas, new StringBuilder(caminhoAtual), somaAtual); // Esquerda
                percorrerRamo(linha - 1, coluna, caminhos, somas, new StringBuilder(caminhoAtual), somaAtual);     // Centro
                percorrerRamo(linha - 1, coluna + 1, caminhos, somas, new StringBuilder(caminhoAtual), somaAtual); // Direita
                break;
            case '|':  // Percurso vertical
                percorrerRamo(linha - 1, coluna, caminhos, somas, new StringBuilder(caminhoAtual), somaAtual);
                break;
            case '\\': // Percurso diagonal esquerda
                percorrerRamo(linha - 1, coluna - 1, caminhos, somas, new StringBuilder(caminhoAtual), somaAtual);
                break;
            case '/':  // Percurso diagonal direita
                percorrerRamo(linha - 1, coluna + 1, caminhos, somas, new StringBuilder(caminhoAtual), somaAtual);
                break;
            default:
                // Caso seja número ou caractere sem bifurcação, seguir o caminho verticalmente
                percorrerRamo(linha - 1, coluna, caminhos, somas, new StringBuilder(caminhoAtual), somaAtual);
                break;
        }
    }

    public int[] acharIndiceRaiz() {
        int ultimaLinha = matriz.length - 1;
        for (int i = 0; i < matriz[ultimaLinha].length; i++) {
            if (matriz[ultimaLinha][i] != ' ') {
                return new int[]{ultimaLinha, i};
            }
        }
        return new int[]{-1, -1};
    }

    public char acharValorRaiz() {
        int[] posicaoRaiz = acharIndiceRaiz();
        int linha = posicaoRaiz[0];
        int coluna = posicaoRaiz[1];
        if (linha == -1 || coluna == -1) {
            return ' ';
        }
        return matriz[linha][coluna];
    }
}
