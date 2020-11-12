public class Main {

    public static void main(String[] args) {

        Sudoku sudoku = new Sudoku();

        for (int i = 0; i<10; i++){
            //auto generate (it can generate unsolvable sudoku sometimes)
            sudoku.generateRandomTable();
            System.out.println("Sudoku - 0"+ (i+1));
            sudoku.display();
            if (sudoku.solve()){
                System.out.println("Solution for Sudoku - 0"+ (i+1));
                sudoku.display();
            }else {
                System.err.println("Unsolvable sudoku it might be random generated");
            }
        }

    }
}
