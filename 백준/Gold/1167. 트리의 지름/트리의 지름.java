import java.io.*;
import java.util.*;

public class Main {
    static int V;
    static List<List<Node>> graph = new ArrayList<>();
    static boolean[] visited;
    static int maxDist;
    static int farthestNode;

    static class Node {
        int to, weight;

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());

        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < V; i++) {
            String line = br.readLine();
            String[] tokens = line.split(" ");
            int v = Integer.parseInt(tokens[0]);
            for (int j = 1; j < tokens.length - 1; j += 2) {
                int to = Integer.parseInt(tokens[j]);
                if (to == -1) break;
                int weight = Integer.parseInt(tokens[j + 1]);
                graph.get(v).add(new Node(to, weight));
            }
        }

        visited = new boolean[V + 1];
        dfs(1, 0);

        visited = new boolean[V + 1];
        maxDist = 0;
        dfs(farthestNode, 0);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(maxDist));
        bw.flush();
    }

    static void dfs(int cur, int dist) {
        visited[cur] = true;

        if (dist > maxDist) {
            maxDist = dist;
            farthestNode = cur;
        }

        for (Node next : graph.get(cur)) {
            if (!visited[next.to]) {
                dfs(next.to, dist + next.weight);
            }
        }
    }
}
