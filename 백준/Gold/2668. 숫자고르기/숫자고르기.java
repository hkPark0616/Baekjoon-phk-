import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int[] arr;
    static boolean[] visited;
    static List<Integer> results = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        
        arr = new int[N + 1];
        
        for(int i = 1; i <= N; i++) {
            int n = Integer.parseInt(br.readLine());
            arr[i] = n;
        }

        for(int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            visited[i] = true; 
            dfs(i, i);
        }

        Collections.sort(results);
        
        System.out.println(results.size());
        
        for(int result: results) {
            System.out.println(result);
        }
    }

    static void dfs(int start, int now) {
        int next = arr[now];
        
        if(!visited[next]) {
            visited[next] = true;
            dfs(start, next);
            visited[next] = false;
        }

        if(next == start) {
            results.add(start);
        } 
    }
}