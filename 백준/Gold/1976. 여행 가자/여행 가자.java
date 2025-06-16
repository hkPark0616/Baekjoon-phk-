import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int[] parent;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));        
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        // union으로 도시 연결하고
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                int connection = Integer.parseInt(st.nextToken());
                if(connection == 1) {
                    union(i, j);
                }
            }
        }

        // 여행 계획에 포함된 모든 도시가 같은 집합(부모)을 가지는지 확인
        // 그렇지 않으면 여행 경로대로 이동할 수 없음
        st = new StringTokenizer(br.readLine());
        int first = Integer.parseInt(st.nextToken());
        boolean possible = true;
        
        for(int i = 1; i < M; i++) {
            int nextCity = Integer.parseInt(st.nextToken());
            if(!isSame(first, nextCity)) {
                possible = false;
                break;
            }
        }
                    
        System.out.println(possible ? "YES" : "NO");
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) {
            parent[b] = a;
        }
    }

    static int find(int a) {
        if(a == parent[a]) {
            return parent[a];
        }

        return parent[a] = find(parent[a]);
    }

    static boolean isSame(int a, int b) {
        return find(a) == find(b);
    }
}