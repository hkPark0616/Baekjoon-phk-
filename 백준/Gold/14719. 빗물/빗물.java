import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int H, W, block[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        block = new int[H][W];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < W; i++){
            int n = Integer.parseInt(st.nextToken());
            for(int j = H - 1; j >= H - n; j--){
                block[j][i] = 1;
            }
        }

        int answer = 0;

        for(int i = 0; i < H; i++){
            int left = 0;
            for(int j = 0; j < W; j++){
                if(block[i][j] == 1){
                    left = j;
                    break;
                }
            }

            int right = 0;
            for(int j = W - 1; j >= 0; j--){
                if(block[i][j] == 1){
                    right = j;
                    break;
                }
            }

            for(int j = left; j < right; j++){
                if(block[i][j] == 0){
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}