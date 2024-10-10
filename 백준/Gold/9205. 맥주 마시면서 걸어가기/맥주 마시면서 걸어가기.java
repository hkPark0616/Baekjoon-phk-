import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;

    static class Point{
        int x;
        int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            Point[] points = new Point[N + 2];
            boolean[] visited = new boolean[N + 2];
            for(int i = 0; i < N + 2; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                points[i] = new Point(x, y);
            }

            if(bfs(points, visited)){
                sb.append("happy\n");
            }else{
                sb.append("sad\n");
            }
        }

        System.out.println(sb.toString());
    }

    static boolean dist(Point p1, Point p2){
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y) <= 1000;
    }

    static boolean bfs(Point[] p, boolean[] visited){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(p[0]);
        visited[0] = true;

        while(!queue.isEmpty()){
            Point point = queue.poll();

            if(point.x == p[p.length - 1].x && point.y == p[p.length - 1].y){
                return true;
            }

            for(int i = 0; i < p.length; i++){
                if(!visited[i] && dist(point, p[i])){
                    queue.offer(p[i]);
                    visited[i] = true;
                }
            }
        }
        return false;
    }
}