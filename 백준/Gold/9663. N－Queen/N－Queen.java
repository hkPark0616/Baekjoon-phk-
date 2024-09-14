import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, answer;
    static int[] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N];
        answer = 0;

        NQueen(0);
        System.out.println(answer);
    }

    static void NQueen(int rowNum){
        if(rowNum == N){
            answer++;
            return;
        }

        for(int i = 0; i < N; i++){
            map[rowNum] = i;
            if(check(rowNum)){
                NQueen(rowNum + 1);
            }
        }
    }

    static boolean check(int rowNum){
        for(int i = 0; i < rowNum; i++){
            if(map[rowNum] == map[i] || Math.abs(map[rowNum] - map[i]) == rowNum - i){
                return false;
            }
        }
        return true;
    }
}