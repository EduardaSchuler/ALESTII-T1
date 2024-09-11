public class Nodo {
    private int pontuacao;
    private Nodo esquerda;
    private Nodo direita;
    private boolean folha;

    public Nodo(int pontuacao, boolean folha){
        this.pontuacao = pontuacao;
        this.folha = folha;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public Nodo getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Nodo esquerda) {
        this.esquerda = esquerda;
    }

    public Nodo getDireita() {
        return direita;
    }

    public void setDireita(Nodo direita) {
        this.direita = direita;
    }

    public boolean isFolha() {
        return folha;
    }
}