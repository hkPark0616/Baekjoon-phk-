import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int INF = 1000000000;
    static List<List<int[]>> graph;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());

            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                int D = Integer.parseInt(st.nextToken());

                graph.get(A).add(new int[] {B, D});
                graph.get(B).add(new int[] {A, D});
            }

            System.out.println(dfs(1, -1));
        }
    }

    static int dfs(int cur, int parent) {
        int result = 0;
        boolean isLeaf = true; // 자식이 없는 노드 판별

        for(int[] arr: graph.get(cur)) {
            int next = arr[0];
            int cnt = arr[1];

            if(next == parent) continue; // 무한 재귀 방지

            isLeaf = false;
            
            int cost = dfs(next, cur); // 현재 노드(cur)에서 자식 노드(next)의 서브트리 전체를 지키려면 얼마 드는지

            result += Math.min(cost, cnt);
        }

        if(isLeaf) {
            if(parent == -1) return 0;
            return INF; // 자식이 없으면 부모가 
        }
        
        return result;
    }
}