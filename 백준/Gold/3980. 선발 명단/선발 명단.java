import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int arr[][];
    static boolean[] selected;
    static int answer;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; tc++) {
            arr = new int[11][11];
            selected = new boolean[11];
            answer = 0;
            
            for(int i = 0; i < 11; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 11; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dfs(0, 0);
            
            sb.append(answer).append("\n");
        }
        
        System.out.println(sb);
    }

    static void dfs(int idx, int sum) {
        if(idx == 11) {
            answer = Math.max(answer, sum);
            return;
        }

        for(int i = 0; i < 11; i++) {
            if(!selected[i] && arr[idx][i] != 0) {
                selected[i] = true;
                dfs(idx + 1, sum + arr[idx][i]);
                selected[i] = false;
            }
        }
    }
}