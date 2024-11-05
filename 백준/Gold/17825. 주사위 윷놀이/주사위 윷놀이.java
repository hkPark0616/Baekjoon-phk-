import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int max;
    static int[] dice;
    static Node[] board;
    static int[] position; // 각 말의 위치

    static class Node{
        int s; // 점수
        int n; // 다음칸
        int j; // 지름길

        public Node(int s, int n, int j){
            this.s = s;
            this.n = n;
            this.j = j;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        dice = new int[10];
        board = new Node[33];
        position = new int[4];
        max = 0;
        for(int i = 0; i < 10; i++){
            dice[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < 21; i++){
            board[i] = new Node(i * 2, i + 1, 0);
        }

        // 갈림길 10, 20, 30
        // 지름길 설정
        board[5].j = 21; // 지름길로 이동: 13 -> 16 -> 19 -> 25 -> 30
        board[10].j = 25; // 지름길로 이동: 22 -> 24 -> 25 -> 30
        board[15].j = 27; // 지름길로 이동: 28 -> 27 -> 26 -> 25 -> 30

        board[21] = new Node(13,22,0);
        board[22] = new Node(16,23,0);
        board[23] = new Node(19,24,0);

        board[24] = new Node(25,30,0);
        board[30] = new Node(30,31,0);
        board[31] = new Node(35,20,0);

        board[25] = new Node(22,26,0); 
        board[26] = new Node(24,24,0);
        board[27] = new Node(28,28,0);
        board[28] = new Node(27,29,0);
        board[29] = new Node(26,24,0); //30번길


        board[20].n = 32; // end
        board[32] = new Node(0, 32, 0); // 종료지점

        dfs(0, 0);

        System.out.println(max);

    }

    static void dfs(int diceIndex, int score){
        if(diceIndex == 10){
            max = Math.max(max, score);
            return;
        }

        int move = dice[diceIndex]; // 현재 말이 이동해야할 칸의 수
        // 각 말 이동 시도
        for(int i = 0; i < 4; i++){
            int current = position[i]; // 현재 위치

            if(current == 32) continue; // 도착 지점에 도달한 말은 이동 못함

            int next = move(current, move);
            
            if(occupied(next, i)) continue; // 다음 위치에 현재 말이 갈 수 있는지 없는지 확인함

            // 이동할 수 있을때
            int prev = position[i];
            position[i] = next;
            dfs(diceIndex + 1, score + board[next].s); // 다음 주사위 수, 다음 위치로 이동하는 점수 +
            position[i] = prev; // 위치 복원
        }
    }

    static int move(int currentPos, int move){
        int pos = currentPos;

        if(board[pos].j != 0) { // 지름길로 갈 수 있는 지점
            pos = board[pos].j; // 지름길로 이동
            move--;
        }

        while(move > 0){
            pos = board[pos].n; // 다음칸으로 이동
            move--;
            if(pos == 32) break; // 종료지점
        }

        return pos;
    }

    static boolean occupied(int pos, int index){
        for(int i = 0; i < 4; i++){
            // 현재말의 위치 index와 다른 말들의 위치 확인
            if(i != index && position[i] == pos && pos != 32){
                return true; // 이동 불가능
            }
        }
        return false; // 이동 가능
    }
}