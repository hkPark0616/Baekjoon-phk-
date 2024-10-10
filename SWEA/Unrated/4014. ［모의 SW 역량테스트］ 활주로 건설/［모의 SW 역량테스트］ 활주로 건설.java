import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, X, map[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            map = new int[N][N];

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.println("#" + t + " " + construction());
        }
    }

    static int construction(){
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            if (rowCheck(i)) { // 행 체크
                cnt++;
            }
        }

        for (int i = 0; i < N; i++) {
            if (colCheck(i)) { // 열 체크
                cnt++;
            }
        }
        return cnt;
    }

    static boolean colCheck(int row){
        boolean[] installed = new boolean[N];
        for(int col = 0; col < N - 1; col++) {
            int diff = map[row][col] - map[row][col + 1];

            if(diff == 0){
                continue;
            }else if(diff == 1){ // 내리막
                // X길이만큼 내리막 경사로를 설치할 수 있는지 확인
                if(col + X >= N) return false;
                // 높이가 다르거나 이미 경사로가 설치된 경우
                for(int i = 1; i <= X; i++){
                    if(map[row][col + i] != map[row][col + 1] || installed[col + i]){
                        return false;
                    }
                    installed[col + i] = true; // 경사로 설치
                }
            }else if(diff == -1){ // 오르막
                // X길이만큼 오르막 경사로를 설치할 수 있는지 확인
                if (col - X + 1 < 0) return false;
                for (int i = 0; i < X; i++) {
                    // 높이가 다르거나 이미 경사로가 설치된 경우
                    if (map[row][col - i] != map[row][col] || installed[col - i]) {
                        return false;
                    }
                    installed[col - i] = true; // 경사로 설치
                }
            }else{
                return false;
            }
        }
        return true;
    }

    static boolean rowCheck(int col) {
        boolean[] installed = new boolean[N];
        for (int row = 0; row < N - 1; row++) {
            int diff = map[row][col] - map[row + 1][col];

            if (diff == 0) {
                continue;
            } else if (diff == 1) { // 내리막
                // X길이만큼 내리막 경사로를 설치할 수 있는지 확인
                if (row + X >= N) return false; // 범위를 벗어나면 불가능
                for (int i = 1; i <= X; i++) {
                    // 높이가 다르거나 이미 경사로가 설치된 경우
                    if (map[row + i][col] != map[row + 1][col] || installed[row + i]) {
                        return false;
                    }
                    installed[row + i] = true; // 경사로 설치
                }
            } else if (diff == -1) { // 오르막
                // X길이만큼 오르막 경사로를 설치할 수 있는지 확인
                if (row - X + 1 < 0) return false; // 범위를 벗어나면 불가능
                for (int i = 0; i < X; i++) {
                    // 높이가 다르거나 이미 경사로가 설치된 경우
                    if (map[row - i][col] != map[row][col] || installed[row - i]) {
                        return false;
                    }
                    installed[row - i] = true; // 경사로 설치
                }
            } else { // 높이 차이가 1이 아닌 경우 경사로 설치 불가
                return false;
            }
        }
        return true;
    }
}