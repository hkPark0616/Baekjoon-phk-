import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, parent[];
    static List<Edge> graph;

    static class Edge {
        int start;
        int end;
        int weight;

        Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()) + 1;
        M = Integer.parseInt(st.nextToken()) + 1;
        graph = new ArrayList<>();

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.add(new Edge(a, b, w));
        }

        parent = new int[N];
        for(int i = 0; i < N; i++){
            parent[i] = i;
        }
        
        // 오름차순
        Collections.sort(graph, (a, b) -> a.weight - b.weight);

        int answer1 = 0;
        for(int i = 0; i < graph.size(); i++){
            Edge e = graph.get(i);

            if(!isSame(e.start, e.end)){
                union(e.start, e.end);
                // 오르막길만 넣으면 최악?
                if(e.weight == 0){
                    answer1 ++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        int answer2 = 0;
        // 거꾸로 하면 최상이 될거임
        for(int i = graph.size() - 1; i >= 0; i--){
            Edge e = graph.get(i);

            if(!isSame(e.start, e.end)){
                union(e.start, e.end);

                if(e.weight == 0){
                    answer2 ++;
                }
            }
        }
        
        // 최악 - 최선
        System.out.println((int)(Math.pow(answer1, 2) - Math.pow(answer2, 2)));
    }

    static void union(int a, int b){
        a = parent[a];
        b = parent[b];

        if(a != b){
            parent[b] = a;
        }
    }

    static int find(int a){
        if(a == parent[a]){
            return parent[a];
        }

        return parent[a] = find(parent[a]);
    }

    static boolean isSame(int a, int b){
        return find(a) == find(b);
    }
}