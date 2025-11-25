import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for(int i = 0; i <= N; i++) parent[i] = i;

        // 이미 연결된 두 정점 연결 -> 사이클 -> 제거
        int rm = 0;
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if(!isSame(u, v)) {
                union(u, v);
            } else {
                rm++; // 제거 연산 횟수
            }
        }

        // 연결해야 하는 간선 수 -> 연결된 뉴런의 개수 - 1
        Set<Integer> roots = new HashSet<>();
        for(int i = 1; i <= N; i++) {
            roots.add(find(i));
        }

        // 연결 + 제거 연산 횟수
        System.out.println((roots.size() - 1) + rm);
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