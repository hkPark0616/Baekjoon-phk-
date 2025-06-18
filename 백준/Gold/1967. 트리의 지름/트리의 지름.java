import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static List<List<Node>> graph = new ArrayList<>();
    static boolean[] visited;
    static int maxDist = 0;
    static int farthestNode = 0;
    static class Node {
        int to, weight;
        
        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // 양방향
            graph.get(from).add(new Node(to, weight));
            graph.get(to).add(new Node(from, weight));
        }
        visited = new boolean[N + 1];

        // 1번 도드에서 가장 먼 노드 찾기
        dfs(1, 0);

        // 가장 먼 노드(farthestNode)에서 트리 지름 찾기
        visited = new boolean[N + 1];
        maxDist = 0;
        dfs(farthestNode, 0);

        System.out.println(maxDist);
    }

    static void dfs(int cur, int dist) {
        visited[cur] = true;

        if(dist > maxDist) {
            maxDist = dist;
            farthestNode = cur;
        }

        for(Node nextNode: graph.get(cur)) {
            if(!visited[nextNode.to]){
                dfs(nextNode.to, dist + nextNode.weight);
            }
        }
    }
}