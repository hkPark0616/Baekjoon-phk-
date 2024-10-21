import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, Fuel;
    static int startX, startY, currentP;
    static int[][] map;
    static List<Passenger> passengers = new ArrayList<>();
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static class Passenger implements Comparable<Passenger> {
        int x, y, d;

        public Passenger(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public int compareTo(Passenger o) {
            if(this.d == o.d){
                if(this.x == o.x)
                    return this.y - o.y;	//거리와 행이 같으면 열 오름차순
                else
                    return this.x - o.x;	//거리가 같으면 행 오름차순
            }
            return this.d - o.d;	//거리 오름차순
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Fuel = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        startX = Integer.parseInt(st.nextToken()) - 1;
        startY = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 2; i < M + 2; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;
            // 고객 정보 저장, 2 이상의 값으로
            map[x1][y1] = i;

            // 목적지 저장
            passengers.add(new Passenger(x2, y2, 0));
        }

        int cnt = 0;
        while(cnt < M){
            //최단 거리 고객 탐색
            int temp1 = findPassenger(map);
            if(Fuel - temp1 <= 0 || temp1 == -1)	//고객에게 갈 때 연료가 부족할 때
                break;

            //목적지 최단 거리 탐색
            int temp2 = searchDestination(map);
            if(Fuel - (temp1 + temp2) < 0 || temp2 == -1)	//목적지에 갈 때 연료가 부족할 때
                break;
            else
                Fuel += temp2 - temp1;		//temp1 : 고객갈 때 연료, temp2 : 목적지 갈 때 연료
            cnt++;	//고객 1명 운행 완료!
        }
        //모든 고객 운행 완료시
        if(cnt == M)
            System.out.println(Fuel);	//남은 연료 BufferedWriter 저장
        else	//모든 고객 운행 미완료시
            System.out.println(-1);

    }

    static int findPassenger(int[][] map){
        PriorityQueue<Passenger> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];
        pq.offer(new Passenger(startX, startY, 0));
        visited[startX][startY] = true;

        while(!pq.isEmpty()){
            Passenger passenger = pq.poll();
            if(map[passenger.x][passenger.y] > 1){
                // 가까운 고객 찾으면, 출발점 갱신
                currentP = map[passenger.x][passenger.y];
                startX = passenger.x;
                startY = passenger.y;
                map[passenger.x][passenger.y] = 0;

                return passenger.d;
            }

            for(int d = 0; d < 4; d++){
                int nx = passenger.x + deltas[d][0];
                int ny = passenger.y + deltas[d][1];

                if(nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && map[nx][ny] != 1){
                    visited[nx][ny] = true;
                    pq.offer(new Passenger(nx, ny, passenger.d + 1));
                }
            }
        }
        return -1;
    }

    static int searchDestination(int[][] map){
        PriorityQueue<Passenger> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];
        pq.offer(new Passenger(startX, startY, 0));
        visited[startX][startY] = true;

        int destinationX  = passengers.get(currentP - 2).x;
        int destinationY = passengers.get(currentP - 2).y;

        while (!pq.isEmpty()){
            Passenger passenger = pq.poll();
            if(passenger.x == destinationX && passenger.y == destinationY){
                startX = destinationX;
                startY = destinationY;
                return passenger.d;
            }

            for(int d = 0; d < 4; d++){
                int nx = passenger.x + deltas[d][0];
                int ny = passenger.y + deltas[d][1];

                if(nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && map[nx][ny] != 1){
                    visited[nx][ny] = true;
                    pq.offer(new Passenger(nx, ny, passenger.d + 1));
                }
            }
        }

        return -1;
    }
}