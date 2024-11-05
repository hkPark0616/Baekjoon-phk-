import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, count;
    static int[] arr;
    static boolean[] isSelected, hasTeam; // 벙뮨 여부와 팀 완성 여부
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for(int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            arr = new int[N + 1];
            isSelected = new boolean[N + 1];
            hasTeam = new boolean[N + 1];
            count = 0;

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= N; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            // 아직 팀이 없는 학생들
            for(int i = 1; i <= N; i++){
                if(!hasTeam[i]){
                    dfs(i);
                }
            }

            System.out.println(N - count);
        }
    }

    static void dfs(int n){
        // 선택된 팀원
        if(isSelected[n]){
            hasTeam[n] = true; // 팀 편성 완료
            count++; // 팀 편성을 완료한 인원+
        }else{
            isSelected[n] = true; // 선택되지 않았으면 선택처리
        }

        // 다음 학생이 팀 결성을 아직 못한 경우
        if(!hasTeam[arr[n]]){
            dfs(arr[n]);
        }

        isSelected[n] = false;
        hasTeam[n] = true;
    }
}