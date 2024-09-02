import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, d, k, c, sushi[], visited[];
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        max = 0;

        visited = new int[d + 1]; // 초밥 종류
        sushi = new int[N];
        for(int i = 0; i < N; i++){
            sushi[i] = Integer.parseInt(br.readLine());
        }

        int cnt = 0;
        // 초밥 k개 만큼 넣어줌
        for(int i = 0; i < k; i++){
            if(visited[sushi[i]] == 0) cnt++;
            visited[sushi[i]]++;
        }

        max = cnt;

        for(int i = 1; i <= N; i++){
            if(max <= cnt){
                // 큰 값 있으면 큰 값 저장하는데
                if(visited[c] == 0){ // 쿠폰 초밥 안먹었으면 +1 해줌
                    max++;
                }else{
                    max = cnt;
                }
            }
            
            // 맨 왼쪽 초밥 제거
            visited[sushi[i - 1]]--;
            // 뺀 초밥 개수 감소
            // 해당 초밥이 중복되어 있으면,서로 다른 초밥의 개수는 변하지 않으므로
            // 중복된 초밥을 다 제거해야 초밥 개수 감소
            if(visited[sushi[i - 1]] == 0) cnt--;

            // 초밥 추가하는데, 초밥이 윈도우 내에 처음 등장하는지 확인함
            // 처음 등장한거면 개수 증가
            if(visited[sushi[(i + k - 1) % N]] == 0) cnt++;
            // 해당 초밥의 개수 증가
            visited[sushi[(i + k - 1) % N]]++;
        }

        System.out.println(max);
    }
}
