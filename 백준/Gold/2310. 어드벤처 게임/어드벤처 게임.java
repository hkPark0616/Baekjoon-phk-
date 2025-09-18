import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int[] roomType; // 0:E, 1:L, 2:T
    static int[] roomCost;
    static boolean[][] visited;
    static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            roomType = new int[N + 1];
            roomCost = new int[N + 1];
            graph = new ArrayList<>();
            for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                char c = st.nextToken().charAt(0);
                int cost = Integer.parseInt(st.nextToken());

                if (c == 'E') roomType[i] = 0;
                else if (c == 'L') roomType[i] = 1;
                else if (c == 'T') roomType[i] = 2;
                roomCost[i] = cost;

                while (true) {
                    int n = Integer.parseInt(st.nextToken());
                    if (n == 0) break;
                    graph.get(i).add(n);
                }
            }
            
            visited = new boolean[N + 1][501];
            sb.append(dfs(1, 0) ? "Yes" : "No").append("\n");
        }

        System.out.println(sb);
    }

    static boolean dfs(int room, int gold) {
        if (roomType[room] == 1) {
            gold = Math.max(gold, roomCost[room]);
        } else if (roomType[room] == 2) {
            if (gold < roomCost[room]) return false;
            gold -= roomCost[room];
        }

        if(room == N) return true;

        if(visited[room][gold]) return false;
        visited[room][gold] = true;

        for(int next: graph.get(room)) if(dfs(next, gold)) return true;

        return false;
    }
}