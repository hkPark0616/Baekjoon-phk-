import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int[] parent;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        for(int i = 0; i <= N; i++) parent[i] = i;

        for(int i = 0; i < N - 2; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            union(A, B);
        }

        
        for(int i = 1; i <= N; i++) {
            for(int j = i + 1; j <= N; j++) {
                if(find(i) != find(j)) {
                    System.out.println(i + " " + j);
                    return;
                }
            }
        }
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
}