public class App{
    public static void main(String[] args) {
        ManipulaMatriz matriz = new ManipulaMatriz();
        char[][] matrizArvore = Reader.readFile("casof30.txt");
        for (int i = 0; i < matrizArvore.length; i++) {
            System.out.println(matrizArvore[i]);
    }
        System.out.println("raiz: " + matriz.acharValorRaiz());
        System.out.println(matriz.encontrarCaminhoComMaiorSoma());
    }
}