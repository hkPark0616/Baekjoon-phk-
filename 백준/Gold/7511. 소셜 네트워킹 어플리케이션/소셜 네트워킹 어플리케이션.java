import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, k, m;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine());
            sb.append("Scenario ").append(t).append(":\n");

            parent = new int[n];
            for(int i = 0; i < n; i++) parent[i] = i;
            
            // 친구관계: 유저 a와 b가 친구
            k = Integer.parseInt(br.readLine());
            for(int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                union(a, b);
            }

            // 구해야하는 쌍
            m = Integer.parseInt(br.readLine());
            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                if(isSame(u, v)) sb.append(1).append("\n");
                else sb.append(0).append("\n");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) {
            parent[b] = a;
        }
    }

    static int find(int a) {
        if(a == parent[a]) return parent[a];

        return parent[a] = find(parent[a]);
    }

    static boolean isSame(int a, int b) {
        return find(a) == find(b);
    }
}