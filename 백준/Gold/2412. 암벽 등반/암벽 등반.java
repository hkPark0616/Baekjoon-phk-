import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, T;
    static Map<Integer, List<Integer>> map = new HashMap<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 같은 x에 있는 모든 y(높이)들을 하나의 그룹으로
            map.putIfAbsent(x, new ArrayList<>());
            map.get(x).add(y);
        }
                
        System.out.println(bfs());
    }

    static int bfs() {
        Set<String> visited = new HashSet<>();
        // 좌표 범위가 크고, 홈의 좌표들이 비연속이라
        ArrayDeque<int[]> q = new ArrayDeque<>();

        q.offer(new int[] {0, 0, 0});
        visited.add("0,0");

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int cnt = cur[2];

            if(y == T) return cnt;

            // |a - x| ≤ 2 범위
            for(int nx = x - 2; nx <= x + 2; nx++) { 
                if(!map.containsKey(nx)) continue;

                // |b - y| ≤ 2 범위
                for(int ny: map.get(nx)) {
                    if(Math.abs(ny - y) > 2) continue;
                    String key = nx + "," + ny;
                    if(visited.contains(key)) continue;
                    visited.add(key);
                    q.offer(new int[] {nx, ny, cnt + 1});
                }
            }
        }
        
        return -1;
    }
}