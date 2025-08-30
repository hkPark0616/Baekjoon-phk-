import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0},{1, 0},{0, -1},{0, 1}};
    static class Block implements Comparable<Block> {
        int x, y;

        public Block(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Block o) {
            if(this.x == o.x) return Integer.compare(this.y, o.y);
            return this.x - o.x;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        List<List<Block>> groups;
        int result = 0;
        while(true) {
            visited = new boolean[N][N];
            groups = new ArrayList<>();
            
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(map[i][j] > 0 && !visited[i][j]) {
                        List<Block> group = bfs(i, j);
                        if (group != null) groups.add(group);
                    }
                }
            }

            if(groups.isEmpty()) break;

            List<Block> selectedGroup = getBlockGroup(groups);
            result += selectedGroup.size() * selectedGroup.size();
            
            // 블록 제거
            for(Block b: selectedGroup) map[b.x][b.y] = -1000;

            gravity();
            rotate();
            gravity();
        }

        System.out.println(result);
    }

    static void gravity() {
        for (int j = 0; j < N; j++) {
            for (int i = N - 1; i >= 0; i--) {
                if(map[i][j] == -1000) {
                    int ni = i - 1;
                    while(ni >= 0 && map[ni][j] == -1000) ni--;
                    if(ni < 0 || map[ni][j] == -1) continue;
                    map[i][j] = map[ni][j];
                    map[ni][j] = -1000;
                }
            }
        }
    }

    static void rotate() {
        int[][] rotatedMap = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                rotatedMap[N - 1 - j][i] = map[i][j];
            }
        }
        map = rotatedMap;
    }

    static List<Block> getBlockGroup(List<List<Block>> groups) {
        groups.sort((g1, g2) -> {
            // 크기가 가장 큰 블록 그룹
           if(g1.size() != g2.size()) return g2.size() - g1.size();

            // 여러 개 -> 무지개 블록 수가 가장 많은 블록 그룹
            int rainbowBlock1 = 0;
            int rainbowBlock2 = 0;
            for (Block b : g1) if (map[b.x][b.y] == 0) rainbowBlock1++;
            for (Block b : g2) if (map[b.x][b.y] == 0) rainbowBlock2++;
            
            if(rainbowBlock1 != rainbowBlock2) return rainbowBlock2 - rainbowBlock1;
    
            // 그러한 블록도 여러개 -> 기준 블록의 행이 가장 큰 것 -> 열이 가장 큰 것
            Block baseBlock1 = getBaseBlock(g1);
            Block baseBlock2 = getBaseBlock(g2);

            if(baseBlock1.x != baseBlock2.x) return baseBlock2.x - baseBlock1.x;
            return baseBlock2.y - baseBlock1.y;
            
        });
        
        return groups.get(0);
    }

    static Block getBaseBlock(List<Block> group) {
        Collections.sort(group);

        for(Block b: group) {
            if(map[b.x][b.y] > 0) return b;
        }

        return null;
    }

    static List<Block> bfs(int a, int b) {
        if(map[a][b] <= 0) return null;

        List<Block> group = new ArrayList<>();
        ArrayDeque<Block> q = new ArrayDeque<>();
        boolean[][] tempVisited = new boolean[N][N]; // 무지개 체크를 위한

        q.offer(new Block(a, b));
        tempVisited[a][b] = true;
        visited[a][b] = true;
        group.add(new Block(a, b));

        while(!q.isEmpty()) {
            Block cur = q.poll();
            int x = cur.x;
            int y = cur.y;

            for(int[] delta: deltas) {
                int nx = x + delta[0];
                int ny = y + delta[1];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(tempVisited[nx][ny] || map[nx][ny] == -1) continue;

                // 현재 일반 블럭과 같은색 or 무지개
                if(map[a][b] == map[nx][ny] || map[nx][ny] == 0) {
                    q.offer(new Block(nx, ny));
                    tempVisited[nx][ny] = true;
                    group.add(new Block(nx, ny));
                    // 일반 블록 방문 처리
                    if(map[nx][ny] > 0) visited[nx][ny] = true;
                }
            }
        }
        

        return group.size() >= 2 ? group : null;
    }
}