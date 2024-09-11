import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K, answer;
    static int[] A;
    static int[] robot;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        answer = 0;
        A = new int[N * 2];
        robot = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < A.length; i++){
            int n = Integer.parseInt(st.nextToken());
            A[i] = n;
        }
        operate();

        System.out.println(answer);
    }

    static void operate(){
        while(check()){
            // 벨트 회전
            int temp = A[A.length - 1];
            for(int i = A.length - 1; i > 0; i--){
                A[i] = A[i - 1];
            }
            A[0] = temp;

            // 벨트랑 로봇 같이 움직임
            for(int i = robot.length - 1; i > 0; i--){
                robot[i] = robot[i - 1];
            }
            robot[0] = 0;

            // 로봇 칸 이동
            robot[N - 1] = 0;
            for(int i = N - 1; i > 0; i--){
                if(robot[i] == 0 && robot[i - 1] == 1 && A[i] >= 1){
                    robot[i] = 1;
                    robot[i - 1] = 0;
                    A[i]--;
                }
            }

            // 1에 로봇 올리기
            if(A[0] >= 1){
                robot[0] = 1;
                A[0]--;
            }

            answer++;
        }
    }

    static boolean check(){
        int cnt = 0;
        for(int i = 0; i < A.length; i++){
            if(A[i] == 0){
                cnt++;
            }
            if(cnt >= K) return false;
        }
        return true;
    }
}