import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, L;
    static List<Integer> list = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        // arr = new int[N];

        st = new StringTokenizer(br.readLine());
        
        for(int i = 0; i < N; i++) list.add(Integer.parseInt(st.nextToken()));
        list.add(0, 0); // 시작점
        list.add(L); // 끝점
        Collections.sort(list);

        System.out.println(parametricSearch());
    }

    static int parametricSearch() {
        int left = 1;
        int right = L;
        int answer = 0;

        while(left <= right) {
            int mid = (left + right) / 2;

            int count = 0;
            // 인접한 휴게소 사이의 거리 계산
            for(int i = 0; i < list.size() - 1; i++) {
                int gap = list.get(i + 1) - list.get(i); // 현재 구간
                count += (gap - 1) / mid; // gap을 mid 이하 간격으로 만들기 위해 추가로 설치해야 하는 휴게소의 개수
            }

            if(count > M) { // 휴게소 더 필요 -> mid 늘림
                left = mid + 1;
            } else { // 휴게소 설치 가능 -> 줄임
                answer = mid;
                right = mid - 1;
            }
        }

        return answer;
    }
}