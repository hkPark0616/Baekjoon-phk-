import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static char[] type;
    static long[] cnt;
    static List<List<Integer>> graph = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        type = new char[N+1];
        cnt = new long[N+1];
        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for(int i = 2; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);
            int a = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            type[i] = c;
            cnt[i] = a;
            graph.get(p).add(i);
        }

        System.out.println(dfs(1));
    }

    static long dfs(int cur) {
        long sum = 0;
        
        for(int c: graph.get(cur)) sum += dfs(c);

        if(type[cur] == 'W') sum -= cnt[cur];
        else if(type[cur] == 'S') sum += cnt[cur];

        return sum <= 0 ? 0 : sum;
    }    
}
