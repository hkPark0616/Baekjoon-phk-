import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, k;
    static int[] friendCost, parent;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        friendCost = new int[N + 1];
        parent = new int[N + 1];
        for(int i = 0; i <= N; i++) parent[i] = i;

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) friendCost[i] = Integer.parseInt(st.nextToken());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            union(v, w);
        }

        boolean[] visited = new boolean[N + 1];
        int cost = 0;
        for(int i = 1; i <= N; i++) {
            int friendRoot = find(i);
            if(!visited[friendRoot]) {
                visited[friendRoot] = true;
                cost += friendCost[friendRoot];
            }
        }

        System.out.println((cost > k) ? "Oh no" : cost);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) {
            // 친구 비용이 더 작은 쪽으로
            if(friendCost[a] < friendCost[b]) {
                parent[b] = a;    
            } else {
                parent[a] = b;
            }
            
        }
    }

    static int find(int a) {
        if(a == parent[a]) return parent[a];

        return parent[a] = find(parent[a]);
    }
}