import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int A, B;
    static String[] cmd = {"D", "S", "L", "R"};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());     
            B = Integer.parseInt(st.nextToken());

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
        boolean[] visited = new boolean[10000];
        String[] commandStr = new String[10000];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;
        commandStr[start] = "";

        while(!q.isEmpty()) {
            int cur = q.poll();

            if(cur == target) return commandStr[cur];

            for(int i = 0; i < 4; i++) {
                int nextNum = operate(i, cur);

                if(!visited[nextNum]) {
                    visited[nextNum] = true;
                    q.offer(nextNum);
                    commandStr[nextNum] = commandStr[cur] + cmd[i];
                }
            }
            
        }

        return "";
    }
}