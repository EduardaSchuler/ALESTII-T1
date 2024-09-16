import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App{
    public static void main(String args[]){
        
    }

    public static char[][] readFile(String file) throws IOException{
        char [][] matriz = null;
        BufferedReader reader = new BufferedReader(new FileReader(file));

        //leitura da dimensão da matriz
        String firstLine = reader.readLine();
        String[] tamanhoMatriz = firstLine.split(" ");
        int numberLines = Integer.parseInt(tamanhoMatriz[0]);
        int numberColumns = Integer.parseInt(tamanhoMatriz[1]);

        //montagem da matriz
        matriz = new char[numberLines][numberColumns];
        for(int i = 0; i < numberLines; i++){
            String line = reader.readLine();
            if (line != null) {
                matriz[i] = line.toCharArray();
            }
        }

        return matriz;
    }
}