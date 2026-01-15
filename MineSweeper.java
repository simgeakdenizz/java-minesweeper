import java.util.Random;
import java.util.Scanner;


public class MineSweeper {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public static void main(String[] args) {
        while (true){
            initializeGame();
        }
    }

    public static void initializeGame(){
        Scanner sc = new Scanner(System.in);
        int [][] table;
        int bomb = 0;
        System.out.println(" Easy / Medium / Hard / Customize:  ");
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
        }else if(difficulty.equalsIgnoreCase("Customize")){
            int row = getSafeInt(sc,"Enter row size: ");
            int column = getSafeInt(sc,"Enter column size: ");
            bomb = getSafeInt(sc,"Enter bomb count: ");
            table = new int[row][column];
        } else{
            System.err.println("Invalid values ");
            return;
        }
        int[][] mineSweeper = setupMinefield(table,bomb);
        runGameLoop(mineSweeper);
    }
    public static int[][] setupMinefield(int [][] n , int m ){
        Random rr = new Random();
        int placedMines = 0;
        for(int i = 0 ; i < n.length ; i++){
            for(int k = 0 ; k < n[0].length ; k++){
                n[i][k] = 9;
            }
        }
        while(placedMines<m){
            int x = rr.nextInt(0 , n[0].length);
            int y = rr.nextInt(0,n.length);
            if(n[y][x]!=-1){
                n[y][x]=-1;
                placedMines++;
            }
        }
        return n;
    }

    public static void runGameLoop (int [][] n){
        int moveCount = 0;
        Scanner sc = new Scanner(System.in);
        int [][] private_n = new int[n.length][n[0].length];
        for(int i = 0 ; i<private_n.length ; i++){
            for(int k = 0 ; k < private_n[0].length ; k++){
                private_n[i][k]=9;
            }
        }
        while(!checkWinCondition(n,private_n)){
            System.out.println("A) Choose safe places\nB) Choose mine place(you can retake it)\nC) New game\nD) Close ");
            String choice = sc.nextLine();
            if(choice.equalsIgnoreCase("A")){
                int x = getSafeInt(sc, "Enter column: ") - 1;
                int y = getSafeInt(sc, "Enter row: ") - 1;
                sc.nextLine();
                moveCount++;
                if(x < 0 || x >= n[0].length || y < 0 || y >= n.length) {
                    System.out.println(ANSI_RED + "Invalid coordinates! Please stay within the grid boundaries." + ANSI_RESET);
                    moveCount--;
                    continue;
                }
                if(n[y][x]== -1) {
                    System.out.println("BOOM!");
                    renderBoard(n);
                    System.out.println("Move count: " + moveCount);
                    System.out.println("\n\n\n\n");
                    break;
                }
                openCell(x,y,n,private_n);
                renderBoard(private_n);
            } else if (choice.equalsIgnoreCase("B")) {
                int x = getSafeInt(sc, "Enter column: ") - 1;
                int y = getSafeInt(sc, "Enter row: ") - 1;
                sc.nextLine();
                moveCount++;
                toggleFlag(x,y,n,private_n);
                renderBoard(private_n);
            } else if (choice.equalsIgnoreCase("C")) {
                System.out.println("Restarting game...");
                return;
            } else if (choice.equalsIgnoreCase("D")) {
                System.out.println("Closing game. Goodbye!");
                System.exit(0);
            } else{
                System.err.println("Invalid choice");
            }
        }
        if(checkWinCondition(n,private_n)){
            System.out.println("Supercalifragilisticexpialidocious!");
            System.out.println("Move count: " + moveCount);
            System.out.println("\n\n\n\n");
        }
    }

    public static int countAdjacentMines(int x , int y , int [][] n){
        int count = 0;
        for(int j = -1 ; j < 2; j++){
            for(int k = -1 ; k < 2 ; k++){
                int checkY = y + j;
                int checkX = x + k;
                if((checkY >= 0) && (checkX >= 0) && (checkY < n.length) && (checkX < n[0].length) ){
                    if((n[checkY][checkX]==-1)){
                        count++;
                    }
                }
            }
        }return count;
    }

    public static void openCell(int x, int y, int[][] n, int[][] private_n) {
        if (y < 0 || y >= n.length || x < 0 || x >= n[0].length) return;
        if (private_n[y][x] == 9){
            int count = countAdjacentMines(x, y, n);
            private_n[y][x] = count;
            if (count == 0) {
                for (int j = -1; j <= 1; j++) {
                    for (int k = -1; k <= 1; k++) {
                        openCell(x + k, y + j, n, private_n);
                    }
                }
            }
        }else if(private_n[y][x] == -2){
            System.out.println("You must retake flag");
            return;
        }
    }

    public static void toggleFlag(int x, int y, int[][] n, int[][] private_n){
        if (y < 0 || y >= n.length || x < 0 || x >= n[0].length) return;
        if (private_n[y][x] == 9) {
            private_n[y][x]=-2;
            return;
        }
        else if(private_n[y][x]== -2 ){
            private_n[y][x]= 9 ;
            return;
        }else{
            return;
        }
    }

    public static void renderBoard(int [][]n){
        int totalWidth = n[0].length * 3 + 4;
        int dashes = Math.max(0, (totalWidth - 9) / 2);
        for(int i = 0 ; i < dashes ; i++){
            System.out.print("=");
        }
        System.out.print("MİNEFİELD");
        for(int i = 0 ; i < dashes ; i++){
            System.out.print("=");
        }
        System.out.println();
        System.out.print("     ");
        for(int i = 1 ; i <= n[0].length ; i++){
            System.out.printf("%-3d" , i );
        }
        System.out.println("\n    " + ".".repeat(n[0].length*3));
        for(int i = 0 ; i < n.length ; i++){
            System.out.printf("%2d | " , (i+1));
            for(int k = 0 ; k < n[0].length ; k++){
                if(n[i][k]==9){
                    System.out.print(ANSI_CYAN + "■  " + ANSI_RESET);
                }else if(n[i][k] == -1){
                    System.out.print(ANSI_RED + "*  " + ANSI_RESET);
                } else if (n[i][k] == -2) {
                    System.out.print(ANSI_PURPLE + "▲  " + ANSI_RESET);
                } else {
                    System.out.printf(ANSI_YELLOW + "%-3d" + ANSI_RESET, n[i][k]);
                }
            }
            System.out.println();
        }
    }
    public static boolean checkWinCondition(int [][] n , int[][] private_n ) {
        int count1 = 0;
        int countHidden = 0;
        for (int i = 0; i < n.length; i++) {
            for (int k = 0; k < n[0].length; k++) {
                if (n[i][k] == -1) {
                    count1++;
                }
                if ((private_n[i][k] == 9) || (private_n[i][k] == -2)  ) {
                    countHidden++;
                }
            }
        }
        return (count1 == countHidden);
    }

    public static int getSafeInt(Scanner sc , String message){
        while (true) {
            System.out.print(message);
            if (sc.hasNextInt()) {
                int input = sc.nextInt();
                if (input > 0) {
                    return input;
                } else {
                    System.out.println(ANSI_RED + "Error: Value must be greater than 0!" + ANSI_RESET);
                }
            } else {
                System.out.println(ANSI_RED + "Error: Please enter a valid number!" + ANSI_RESET);
                sc.next();
            }
        }
    }

}
