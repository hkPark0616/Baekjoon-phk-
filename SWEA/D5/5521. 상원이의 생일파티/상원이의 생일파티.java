import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static int N, M, answer;
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

            for(int i = 0; i <= N; i++){
                graph.add(new ArrayList<>());
            }

            for(int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph.get(a).add(b);
                graph.get(b).add(a);
            }

            answer = 0;
            // 상원이(1)부터 시작
            bfs(1);

            System.out.println("#" + t + " " + answer);
        }
    }

    static void bfs(int start){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {start, 0}); // 시작지점과 관계 깊이
        visited[start] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();

            int n = cur[0];
            int depth = cur[1]; // 현재 관계 깊이

            List<Integer> list = graph.get(n); // 친구 관계 리스트

            for(int relation: list){
                if(!visited[relation]){
                    visited[relation] = true;
                    answer++;

                    if(depth < 1){ // 상원이 친구와 친구의 친구까지만
                        q.offer(new int[] {relation, depth + 1});
                    }
                }
            }
        }
    }
}