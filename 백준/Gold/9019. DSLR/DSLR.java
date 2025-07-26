import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int[] prev = new int[10000]; // 이전 숫자
    static char[] how = new char[10000]; // 어떤 명령어 사용해서 왔는지
    static boolean[] visited = new boolean[10000];
    static char[] cmd = {'D', 'S', 'L', 'R'};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());     
            int B = Integer.parseInt(st.nextToken());

            String answer = bfs(A, B);
            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }

    // DSLR: 0 1 2 3
    static int operate(int cmd, int num) {
        int result = 0;
        switch (cmd) {
            case 0:
                result = (num * 2) % 10000;
                break;
            case 1:
                result = num == 0 ? 9999 : num - 1;
                break;
            case 2:
                result = (num % 1000) * 10 + num / 1000;
                break;
            case 3:
                result = (num % 10) * 1000 + num / 10;
                break;
        }
        return result;
    }

    static String bfs(int start, int target) {
        Arrays.fill(visited, false);
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;
        prev[start] = -1;

        while(!q.isEmpty()) {
            int cur = q.poll();

            if(cur == target) break;

            for(int i = 0; i < 4; i++) {
                int nextNum = operate(i, cur);

                if(!visited[nextNum]) {
                    visited[nextNum] = true;
                    prev[nextNum] = cur;
                    how[nextNum] = cmd[i];
                    q.offer(nextNum);
                }
            }
        }

        // target 숫자에서 역으로 가면서 사용했던 명령어 저장하고 거꾸로 리턴
        StringBuilder cmdPath = new StringBuilder();
        for(int cur = target; prev[cur] != -1; cur = prev[cur]) {
            cmdPath.append(how[cur]);
        }

        return cmdPath.reverse().toString();
    }
}