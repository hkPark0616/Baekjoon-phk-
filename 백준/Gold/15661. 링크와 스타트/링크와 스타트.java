import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int min = Integer.MAX_VALUE;
    static int[][] stats;
    static boolean[] selected;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        stats = new int[N][N];
        selected = new boolean[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                stats[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        find(0);

        System.out.println(min);
    }


    static void find(int idx) {
        if(idx == N) {
            List<Integer> teamA = new ArrayList<>();
            List<Integer> teamB = new ArrayList<>();

            for(int i = 0; i < N; i++) {
                if(selected[i]) teamA.add(i);
                else teamB.add(i);
            }

            int sumA = getSum(teamA);
            int sumB = getSum(teamB);

            min = Math.min(min, Math.abs(sumA - sumB));
            
            return;
        }

        // Team A
        selected[idx] = true;
        find(idx + 1);

        // Team B
        selected[idx] = false;
        find(idx + 1);
    }

    static int getSum(List<Integer> team) {
        int sum = 0;
        for(int i = 0; i < team.size(); i++) {
            for(int j = i + 1; j < team.size(); j++) {
                int a = team.get(i);
                int b = team.get(j);

                sum += stats[a][b] + stats[b][a];
            }
        }

        return sum;
    }
}