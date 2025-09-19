import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, X;
    static List<List<Integer>> graph = new ArrayList<>();
    static List<List<Integer>> reverseGraph = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph.get(A).add(B); // A가 B보다 잘함
            reverseGraph.get(B).add(A); // B보다 A가 잘함
        }

        int higher = bfs(reverseGraph, X); // X 보다 앞선
        int lower = bfs(graph, X); // X 보다 뒤

        // 앞선 사람 수 + 1 => 가능한 가장 높은 등수
        // X보다 뒤에 있는 애들을 제외 -> 가능한 가장 낮은 등수
        System.out.println((higher + 1) + " " + (N - lower));
    }

    static int bfs(List<List<Integer>> g, int start) {
        boolean[] visited = new boolean[N + 1];
        ArrayDeque<Integer> q = new ArrayDeque<>();

        visited[start] = true;
        q.offer(start);
        int cnt = 0;

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int next: g.get(cur)) {
                if(!visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                    cnt++;
                }
            }
        }

        return cnt;
    }
}