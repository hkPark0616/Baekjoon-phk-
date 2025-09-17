import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static boolean[] visited;
    static int maxDist = 0;
    static int farthestNode = 0;
    static List<List<int[]>> graph = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 0; i <= 10000; i++) graph.add(new ArrayList<>());
        visited = new boolean[10001];
        
        while(true) {
            String s = br.readLine();

            if (s == null || s.trim().isEmpty()) break;
            
            st = new StringTokenizer(s);
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph.get(A).add(new int[] {B, C});
            graph.get(B).add(new int[] {A, C});
        }

        dfs(1, 0);

        visited = new boolean[10001];
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

        for(int[] next: graph.get(cur)) {
            if(!visited[next[0]]) {
                dfs(next[0], dist + next[1]);
            }
        }
    }
}