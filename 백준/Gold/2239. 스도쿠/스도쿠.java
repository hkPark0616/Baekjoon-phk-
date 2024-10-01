import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[9][9];
        for(int i = 0; i < 9; i++){
            String line = br.readLine();
            for(int j = 0; j < 9; j++){
                map[i][j] = line.charAt(j) - '0';
            }
        }

        sudoku(0, 0);

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }

    }

    static boolean sudoku(int row, int col){
        if(row == 9){
            return true;
        }

        if(col == 9){
            return sudoku(row + 1, 0);
        }

        if(map[row][col] != 0){
            return sudoku(row, col + 1);
        }

        for(int i = 1; i <= 9; i++){
            if(check(row, col, i)){
                map[row][col] = i;

                if(sudoku(row, col + 1)){
                    return true;
                }

                map[row][col] = 0;
            }
        }

        return false;
    }

    // row, col, 3x3 check
    static boolean check(int row, int col, int num){
        // row, col
        for(int i = 0; i < 9; i++){
            if(map[row][i] == num || map[i][col] == num){
                return false;
            }
        }

        // 3x3
        int rowStart = (row / 3) * 3;
        int colStart = (col / 3) * 3;
        for(int i = rowStart; i < rowStart + 3; i++){
            for(int j = colStart; j < colStart + 3; j++){
                if(map[i][j] == num){
                    return false;
                }
            }
        }
        return true;
    }
}