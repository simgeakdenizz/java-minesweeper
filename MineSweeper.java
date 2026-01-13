import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rr = new Random();
        int [][] table;
        int bomb = 0;
        System.out.println(" Easy / Medium / Hard :  ");
        String difficulty = sc.nextLine();
        if (difficulty.equalsIgnoreCase("Easy")){
            table = new int [8][10];
            bomb = 10;
        } else if (difficulty.equalsIgnoreCase("Medium")) {
            table = new int [14][18];
            bomb = 40;
        }else if(difficulty.equalsIgnoreCase("Hard")){
            table = new int [20][24];
            bomb = 99;
        }else{
            System.err.println("Invalid values ");
            table = new int [0][0];
        }
        int[][] mineSweeper = mineSweeper(table,bomb);
        whatIsThisBlock(mineSweeper);
    }
    public static int[][] mineSweeper(int [][] n , int m ){
        Random rr = new Random();
        for(int i = 0 ; i < n.length ; i++){
            for(int k = 0 ; k < n[0].length ; k++){
                n[i][k] = 9;
            }
        }
        for(int i = 0 ; i < m ; i++){
            int x = rr.nextInt(0 , n[0].length);
            int y = rr.nextInt(0,n.length);
            n[y][x]=-1;
        }
        printTable(n);
        return n;
    }

    public static void whatIsThisBlock (int [][] n){
        int number = 0;
        Scanner sc = new Scanner(System.in);
        int [][] private_n = new int[n.length][n[0].length];
        for(int i = 0 ; i<private_n.length ; i++){
            for(int k = 0 ; k < private_n[0].length ; k++){
                private_n[i][k]=9;
            }
        }
        while(!isWin(n,private_n)){
            System.out.println("Enter X: ");
            int x = sc.nextInt()-1;
            System.out.println("Enter Y: ");
            int y = sc.nextInt()-1;
            if(n[y][x]== -1) {
                System.out.println("BOOM!");
                number= -2;
                break;
            }
            int numberOfBombs = 0;
            for(int j = -1 ; j < 2; j++){
                for(int k = -1 ; k < 2 ; k++){
                    int checkY = y + j;
                    int checkX = x + k;
                    if((checkY >= 0) && (checkX >= 0) && (checkY < n.length) && (checkX < n[0].length) && (n[checkY][checkX]==-1)){
                        numberOfBombs ++;
                    }
                }
            }
            private_n[y][x]=numberOfBombs;
            printTable(private_n);
        }
        if(number == -2){
            printTable(n);
        }
        if(isWin(n , private_n)){
            System.out.println("Supercalifragilisticexpialidocious!");
        }
    }

    public static void printTable(int [][]n){
        int line = (n[0].length*4-9)/2;
        for(int i = 0 ; i < line ; i++){
            System.out.print("-");
        }
        System.out.print("MİNEFİELD");
        for(int i = 0 ; i < line ; i++){
            System.out.print("-");
        }
        System.out.println(" ");
        for(int i = 0 ; i < n.length ; i++){
            for(int k = 0 ; k < n[0].length ; k++){
                if(n[i][k]==9){
                    System.out.print(ANSI_CYAN + " ■ " + ANSI_RESET);
                }else if(n[i][k] == -1){
                    System.out.print(ANSI_RED + " * " + ANSI_RESET);
                }else {
                    System.out.print(ANSI_YELLOW);
                    System.out.print(" " +  n[i][k] + " ");
                    System.out.print(ANSI_RESET);
                }
            }
            System.out.println(" ");
        }
    }
    public static boolean isWin(int [][] n , int[][] private_n ) {
        int count1 = 0;
        int count9 = 0;
        for (int i = 0; i < n.length; i++) {
            for (int k = 0; k < n[0].length; k++) {
                if (n[i][k] == -1) {
                    count1++;
                }
                if (private_n[i][k] == 9) {
                    count9++;
                }
            }
        }
        if (count1 == count9) {
            return true;
        }
        return false;
    }
}
