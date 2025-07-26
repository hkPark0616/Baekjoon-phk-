import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int A, B;
    static boolean[] isPrime = new boolean[10000];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        findPrime();
        
        for(int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());     
            B = Integer.parseInt(st.nextToken());

            int answer = bfs(A, B);

            sb.append(answer == -1 ? "Impossible" : answer).append("\n");
        }

        System.out.println(sb);
    }

    static void findPrime() {
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for(int i = 2; i * i < 10000; i++) {
            if(isPrime[i]) {
                for(int j = i*i; j < 10000; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    static int bfs(int start, int target) {
        boolean[] visited = new boolean[10000];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {start, 0});
        visited[start] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int n = cur[0];
            int cnt = cur[1];

            if(n == target) return cnt;

            for(int i = 0; i < 4; i++) {
                char[] chars = String.valueOf(n).toCharArray();
                for(char c = '0'; c <= '9'; c++) {
                    if(c == chars[i]) continue;
                    
                    chars[i] = c;
                    int nextNum = Integer.parseInt(String.valueOf(chars));

                    if(nextNum >= 1000 && isPrime[nextNum] && !visited[nextNum]) {
                        q.offer(new int[] {nextNum, cnt + 1});
                        visited[nextNum] = true;
                    }
                }
            }
            
        }

        return -1;
    }
}