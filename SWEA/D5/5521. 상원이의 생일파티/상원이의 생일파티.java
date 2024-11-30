import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int N, M, relations;
    static List<List<Integer>> graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            graph = new ArrayList<>();
            visited = new boolean[N + 1];
            for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());

            for(int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph.get(a).add(b);
                graph.get(b).add(a);
            }

            relations = 0;

            bfs(1);

            System.out.println("#" + t + " " + relations);
        }
    }

    static void bfs(int start){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {start, 0});
        visited[start] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int depth = cur[1];

            List<Integer> list = graph.get(cur[0]);

            for(int i = 0; i < list.size(); i++){
                int n = list.get(i);

                if(!visited[n]){
                    visited[n] = true;
                    relations++;

                    if (depth < 1) {
                        q.offer(new int[] {n, depth + 1});
                    }
                }
            }
        }
    }
}