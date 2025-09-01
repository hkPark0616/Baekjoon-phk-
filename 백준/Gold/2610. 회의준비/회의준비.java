import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int[] parent;
    static int[][] dist;
    static int INF = 1000000000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        for(int i = 0; i <= N; i++) parent[i] = i;

        dist = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(i == j) dist[i][j] = 0;
                else dist[i][j] = INF;
            }
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dist[a][b] = 1;
            dist[b][a] = 1;
            union(a, b);
        }

        floyd();
        
        Map<Integer, List<Integer>> groups = new HashMap<>();
        for(int i = 1; i <= N; i++) {
            int root = find(i);
            groups.putIfAbsent(root, new ArrayList<>());
            groups.get(root).add(i);
        }

        StringBuilder sb = new StringBuilder();
        List<Integer> leaders = new ArrayList<>();
  
        for(Integer key: groups.keySet())  leaders.add(getLeader(groups.get(key)));
        Collections.sort(leaders);
        
        sb.append(leaders.size()).append("\n");
        for(int l: leaders) {
            sb.append(l).append("\n");
        }
          
        System.out.println(sb);
    }

    static int getLeader(List<Integer> group) {
        int leader = 0;
        int min = INF;

        for(int canditate: group) {
            int n = 0;
            for(int otherCandidate: group) {
                n = Math.max(n, dist[canditate][otherCandidate]);
            }

            // 적게 거쳐서 의견을 전달할 수 있는 사람
            if(n < min) {
                min = n;
                leader = canditate;
            }
        }
        return leader;
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

    static void floyd() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }
}