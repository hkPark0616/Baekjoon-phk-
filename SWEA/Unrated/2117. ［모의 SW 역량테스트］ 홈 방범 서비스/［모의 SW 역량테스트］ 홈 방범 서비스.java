import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static int N, M, map[][], max;
    static List<int[]> home;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken()); // 집 비용
            max = 0;
            // 보안회사의 이익 = 제공받는 집 개수 * 집 비용 - 운영수익(마름모 크기)
            home = new ArrayList<>();
            map = new int[N][N];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 1){
                        home.add(new int[]{i, j});
                    }
                }
            }

            for(int k = 1; k <= N + 1; k++){ // 운영 비용
                for(int i = 0; i < N; i++) {
                    for(int j = 0; j < N; j++) {
                        search(i, j, k);
                    }
                }
            }

            System.out.println("#" + t + " " + max);
        }
    }

    // 서비스 할 중앙 위치에서부터 마름모 범위로 집이 존재하면 true, 아니면 false
    // x, y는 집 위치
    // a, b는 서비스 위치
    // size는 서비스 영역
    static boolean check(int x, int y, int a, int b, int size){
        return Math.abs(a - x) + Math.abs(b - y) < size;

    }

    static void search(int x, int y, int size){
        int cnt = 0;
        for(int i = 0; i < home.size(); i++) {
            if(check(home.get(i)[0], home.get(i)[1], x, y, size)){
                cnt++;
            }
        }
        // 보안회사의 이익 = 제공받는 집 개수 * 집 비용 - 운영수익(마름모 크기)
        int cost = cnt * M;
        if(cost >= (size * size + (size - 1) * (size - 1))){
            max = Math.max(max, cnt);
        }
    }
}