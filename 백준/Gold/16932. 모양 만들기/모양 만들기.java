import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, groupId;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Map<Integer, Integer> groupSize = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 그룹 배정
        groupId = 2;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 1) {
                    int size = bfs(i, j, groupId);
                    groupSize.put(groupId, size);
                    groupId++;
                }
            }
        }

        int answer = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0) { // 1로 바꿀 칸 고르기
                    Set<Integer> set = new HashSet<>(); // 그룹 중복 방지
                    int sum = 1;

                    for(int[] delta: deltas) {
                        int nx = i + delta[0];
                        int ny = j + delta[1];

                        if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                        if(map[nx][ny] > 1) { // 인접한 칸이 그룹이 배정된 칸이면
                            int gid = map[nx][ny];

                            if(!set.contains(gid)) {
                                sum += groupSize.get(gid); // groupId에 따른 size 더하기
                                set.add(gid);
                            }
                        }
                    }

                    answer = Math.max(answer, sum);
                }
            }
        }

        System.out.println(answer);
    }

    static int bfs(int a, int b, int id) {
        ArrayDeque<int[]> q = new ArrayDeque<>();

        map[a][b] = id;
        q.offer(new int[] {a, b});
        int size = 1;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            for(int[] delta: deltas) {
                int nx = x + delta[0];
                int ny = y + delta[1];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(map[nx][ny] == 1) { // 아직 그룹 배정 안됨
                    map[nx][ny] = id;
                    q.offer(new int[] {nx, ny});
                    size++;
                }
            }
        }

        return size;
    }
}