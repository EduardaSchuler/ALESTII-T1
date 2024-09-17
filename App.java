
import java.io.IOException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String file = "";
        
        

        System.out.println("Escolha a arvore que deseja percorrer: ");
        System.out.print("\n1 - casod30.txt\n2 - casod60.txt\n3 - casod90.txt\n4 - casod120.txt\n5 - casod150.txt\n6 - casod180.txt\n7 - casod250.txt\n8 - casod300.txt\n9 - caso teste\n\n");

        System.out.print("Sua escolha: ");
        int choose = sc.nextInt();
        
        switch (choose) {
            case 1:
                file = "casof30.txt";
                break;
            case 2:
                file = "casof60.txt";
                break;
            case 3:
                file = "casof90.txt";
                break;
            case 4:
                file = "casof120.txt";
                break;
            case 5:
                file = "casof150.txt";
                break;   
            case 6:
                file = "casof180.txt";
                break;
            case 7:
                file = "casof250.txt";
                break;
            case 8:
                file = "casof300.txt";
                break; 
            case 9:
                file = "teste.txt";
                break;    
            default:
                System.exit(0);
                break;
        }

        Arvore tree = new Arvore(file);

        //tree.imprimeMatriz();

        //tree.displayTree();

        
    }
}