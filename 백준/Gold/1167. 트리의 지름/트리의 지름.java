import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int V;
    static List<List<Node>> graph = new ArrayList<>();
    static boolean[] visited;
    static int maxDist;
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
        StringTokenizer st;

        V = Integer.parseInt(br.readLine());

        for(int i = 0; i <= V; i++) graph.add(new ArrayList<>());

        for(int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            
            while(true) {
                int to = Integer.parseInt(st.nextToken());

                if(to == -1) break;

                int weight = Integer.parseInt(st.nextToken());

                graph.get(v).add(new Node(to, weight));
                graph.get(to).add(new Node(v, weight));
            }
            
            
        }

        visited = new boolean[V + 1];
        dfs(1, 0);


        visited = new boolean[V + 1];
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
            if(!visited[nextNode.to]) {
                dfs(nextNode.to, dist + nextNode.weight);
            }
        }
    }
}