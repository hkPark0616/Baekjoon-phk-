import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int[] parent;
    static Group[] groups;
    static class Group {
        int x, y, r;

        public Group(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());

            parent = new int[N];
            for(int i = 0; i < N; i++) parent[i] = i;

            groups = new Group[N];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int R = Integer.parseInt(st.nextToken());

                groups[i] = new Group(x, y, R);
            }

            int cnt = 0;
            for(int i = 0; i < N; i++) {
                for(int j = i + 1; j < N; j++) {
                    if(isConnected(groups[i], groups[j])) {
                        union(i, j);
                        cnt++;
                    }
                }
            }

            int answer = 0;
            for(int i = 0; i < N; i++) {
                if(i == find(i)) answer++;
            }
            
            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }

    static boolean isConnected(Group g1, Group g2) {
        int dx = g1.x - g2.x;
        int dy = g1.y - g2.y;
        int dist = dx * dx + dy * dy; // Math.sqrt(dx * dx + ny * ny)
        int rsum = g1.r + g2.r;
        return dist <= rsum * rsum; // g1.r + g2.r
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

    static boolean isSame(int a, int b) {
        return find(a) == find(b);
    }
}