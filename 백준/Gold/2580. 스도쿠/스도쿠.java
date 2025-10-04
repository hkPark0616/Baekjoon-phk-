import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int[][] map;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        map = new int[9][9];
        for(int i = 0; i < 9; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sudoku(0, 0);

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean sudoku(int row, int col) {
        if(row == 9) return true; // 끝

        if(col == 9) return sudoku(row + 1, 0); // 열의 끝 -> 다음 행, 첫번째 열로 이동

        if(map[row][col] != 0) return sudoku(row, col + 1); // 이미 숫자 채워진 칸, 다음 칸

        for(int i = 1; i <= 9; i++) {
            if(check(row, col, i)) { // 숫자 확인
                map[row][col] = i;
                if(sudoku(row, col + 1)) return true; // 다음 칸
                map[row][col] = 0;
            }
        }

        return false;
    }

    // 가로, 세로, 3x3 정사각형 확인
    static boolean check(int row, int col, int num) {
        // 가로, 세로
        for(int i = 0; i < 9; i++) {
            if(map[i][col] == num || map[row][i] == num) return false;
        }

        // 3x3 정사각형
        int rowStart = (row / 3) * 3;
        int colStart = (col / 3) * 3;
        for(int i = rowStart; i < rowStart + 3; i++) {
            for(int j = colStart; j < colStart + 3; j++) {
                if(map[i][j] == num) return false;
            }
        }

        return true;
    }
}