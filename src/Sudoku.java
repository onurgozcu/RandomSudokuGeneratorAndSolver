import java.util.Random;

public class Sudoku {

    private char[][] table;
    private static final char UNASSIGNED_CHAR = ' ';
    private final char charArray[] = {'C','N','G','B','I','M','2','1','3'};

    public Sudoku() {
        table = new char[9][9];
    }

    public Sudoku(char table[][]) {
        this.table= table;
    }

    //TODO Methods
    private boolean allowedInRow(int row,char element){
        for (int i = 0; i<9; i++){
            if (table[row][i] == element){
                return false;
            }
        }
        return true;
    }
    private boolean allowedInColumn(int col,char element){
        for (int i = 0; i<9; i++){
            if (table[i][col] == element){
                return false;
            }
        }
        return true;
    }

    private boolean allowedInBox(int row, int col,char element){
        for (int i = row-(row%3); i<row-(row%3)+3; i++){
            for (int j = col-(col%3); j<col-(col%3)+3; j++){
                if (table[i][j] == element){
                    return false;
                }
            }
        }
        return true;
    }
    private boolean isAllowed(int row, int col,char element){
        return allowedInRow(row,element) && allowedInColumn(col, element) && allowedInBox(row, col, element);
    }

    //auto generate (it can generate unsolvable sudoku sometimes)
    public void generateRandomTable(){
        table = new char[9][9];
        for (int i = 0; i<9; i++){
            for (int j = 0; j<9; j++){
                table[i][j] = getRandomChar(i,j);
            }
        }
    }

    public char getRandomChar(int row, int col){
        Random random = new Random();
        int randomIndex = random.nextInt(30);
        if (randomIndex < 9){
            if (isAllowed(row, col, charArray[randomIndex])){
                return charArray[randomIndex];
            }else {
                return getRandomChar(row, col);
            }
        }else {
            return UNASSIGNED_CHAR;
        }
    }

    public void display(){
        System.out.println("_______________________________");
        for (int i = 0; i<9; i++){
            if (i%3 == 0 && i != 0){
                System.out.println("-------------------------------");
            }
            for (int j = 0; j<9; j++){
                if (j%3 == 0){
                    System.out.print("|");
                }
                System.out.print(" " + table[i][j] + " ");
                if (j == 8){
                    System.out.print("|");
                }
            }
            System.out.println("");
        }
        System.out.println("_______________________________");
    }

    public boolean solve(){
        for (int i = 0; i<9; i++){
            for (int j = 0; j<9; j++){
                if (table[i][j] == UNASSIGNED_CHAR){
                    for (int index = 0; index<9; index++){
                        if (isAllowed(i,j,charArray[index])){
                            table[i][j] = charArray[index];
                            if (solve()){
                                return true;
                            } else{
                                table[i][j] = UNASSIGNED_CHAR;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

}
