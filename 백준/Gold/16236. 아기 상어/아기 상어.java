import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, startX, startY;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static BabyShark babyShark;
    static class BabyShark {
        int x, y, size, eat;

        public BabyShark(int x, int y, int size, int eat) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.eat = eat;
        }
    }
    static class Fish implements Comparable<Fish> {
        int x, y, dist;

        public Fish(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Fish f) {
            if(this.dist != f.dist) return this.dist - f.dist; // 거리가 다르면 짧은거 우선
            if(this.x != f.x) return this.x - f.x; // 거리가 같으면 위쪽이 먼저
            return this.y - f.y; // 거리도 같고 위쪽도 같으면 가장 왼쪽꺼
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) babyShark = new BabyShark(i, j, 2, 0);
            }
        }

        int time = 0;
        map[babyShark.x][babyShark.y] = 0;
        
        while(true) {
            Fish fish = findFish();

            if(fish == null) break;

            // 물고기 찾았으면 먹기
            time += fish.dist; // 이동한 칸(시간) 증가
            babyShark.x = fish.x;
            babyShark.y = fish.y;
            babyShark.eat++;

            // 상어 키우기
            if(babyShark.size == babyShark.eat) {
                babyShark.size++;
                babyShark.eat = 0;
            }

            map[fish.x][fish.y] = 0; // 물고기 자리 0
        }

        System.out.println(time);
    }
    
    static Fish findFish() {
        List<Fish> fishList = new ArrayList<>();
        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        q.offer(new int[] {babyShark.x, babyShark.y, 0});
        visited[babyShark.x][babyShark.y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];

            for(int[] delta: deltas) {
                int nx = x + delta[0];
                int ny = y + delta[1];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue; // 범위 확인
                if(visited[nx][ny] || map[nx][ny] > babyShark.size) continue; // 아기상어보다 크면

                visited[nx][ny] = true;

                if(map[nx][ny] != 0 && map[nx][ny] < babyShark.size) { // 먹을 물고기 목록
                    fishList.add(new Fish(nx, ny, dist + 1));
                }

                q.offer(new int[] {nx, ny, dist + 1});
            }
        }

        if(fishList.isEmpty()) return null;

        Collections.sort(fishList);
        return fishList.get(0); // 정렬된거 첫번째
    }
}