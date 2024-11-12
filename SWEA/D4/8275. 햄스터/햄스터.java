import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int N, X, M;
    static int l, r, s;
    static int maxHamsters;
    static int[] cages;
    static int[] result;
    static int[][] conditions;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 햄스터 우리 N개
            X = Integer.parseInt(st.nextToken()); // 각 우리에 0 이상 X마리 이하의 햄스터 있음
            M = Integer.parseInt(st.nextToken()); // M개의 기록

            cages = new int[N]; // 햄스터 우리
            result = new int[N]; // 햄스터 개수 최대 개수 배열
            conditions = new int[M][3]; // 기록 저장
            for(int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                l = Integer.parseInt(st.nextToken()) - 1;
                r = Integer.parseInt(st.nextToken()) - 1;
                s = Integer.parseInt(st.nextToken());
                // l번 우리에서 r번 우리까지의 햄스터 수를 세었더니 s마리였다.

                conditions[i] = new int[]{l, r, s}; // start, end, sum
            }
            maxHamsters = -1;

            dfs(0, 0);

            if (maxHamsters == -1) {
                System.out.println("#" + t + " " + maxHamsters);
            } else {
                System.out.print("#" + t + " ");
                for (int count : result) {

                    System.out.print(count + " ");
                }
                System.out.println();
            }
        }
    }

    static void dfs(int cnt, int sum){
        if(cnt == N){
            // l번 우리에서 r번 우리까지의 햄스터 수를 세었더니 s마리였다. 조건 확인
            if(check()){
                if(sum > maxHamsters){
                    maxHamsters = sum;
                    result = Arrays.copyOf(cages, N);
                }
            }
            return;
        }
        // 각 우리에 0부터 X까지의 햄스터 수를 배치 시도
        for (int i = 0; i <= X; i++) {
            cages[cnt] = i;
            dfs(cnt + 1, sum + i);
        }
    }

    static boolean check() {
        for (int[] condition : conditions) {
            int L = condition[0];
            int R = condition[1];
            int S = condition[2];
            int sum = 0;
            for (int i = L; i <= R; i++) {
                sum += cages[i];
            }
            if (sum != S) {
                return false;
            }
        }
        return true;
    }
}