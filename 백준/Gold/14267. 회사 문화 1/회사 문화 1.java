import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static List<List<Integer>> list = new ArrayList<>();
    static int[] praise;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        praise = new int[N + 1];
        
        for(int i = 0; i <= N; i++) list.add(new ArrayList<>());

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            int p = Integer.parseInt(st.nextToken());
            if(i == 1) continue; // 사장은 상사 없음
            list.get(p).add(i);
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            praise[idx] += p;
        }

        dfs(1); // 사장부터 시작

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(praise[i]).append(" ");
        }
        System.out.println(sb);
    }

    static void dfs(int cur) {
        for(int n: list.get(cur)) {
            praise[n] += praise[cur]; // 상사가 받은 칭찬을 부하에게 더함
            dfs(n); // 해당 부하의 부하에게 칭찬 전파
        }
    }
}