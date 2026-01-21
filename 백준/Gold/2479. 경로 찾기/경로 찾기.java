import java.util.*;
import java.lang.*;
import java.io.*;

// 2479 경로 찾기
class Main {
    static int N, K, A, B;
    static String[] arr;
    static List<List<Integer>> graph = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
  
        arr = new String[N];
        for(int i = 0; i < N; i++) arr[i] = br.readLine();

        for(int i = 0; i < N; i++) graph.add(new ArrayList<>());

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken()) - 1;
        B = Integer.parseInt(st.nextToken()) - 1;

        // 해밍 거리 1인 경우 연결
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (check(arr[i], arr[j])) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }

        bfs();

    }

    static void bfs() {
        boolean[] visited = new boolean[N];
        int[] parent = new int[N];
        Arrays.fill(parent, -1);

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(A);
        visited[A] = true;

        while(!q.isEmpty()) {
            int cur = q.poll();

            if(cur == B) break; // 도착

            for(int next: graph.get(cur)) {
                if(!visited[next]) {
                    visited[next] = true;
                    parent[next] = cur;
                    q.offer(next);
                }
            }
        }

        // 도착 불가능
        if(!visited[B]) {
            System.out.println(-1);
            return;
        }

        List<Integer> path = new ArrayList<>();
        int cur = B;
        while(cur != -1) {
            path.add(cur);
            cur = parent[cur];
        }

        Collections.reverse(path);

        StringBuilder sb = new StringBuilder();
        for(int p: path) sb.append(p + 1).append(" ");

        System.out.println(sb);
    }

    static boolean check(String a, String b) {
        int diff = 0;
        for(int i = 0; i < K; i++) {
            if(a.charAt(i) != b.charAt(i)) {
                diff++;
                if(diff > 1) return false;
            }
        }

        return diff == 1;
    }
}