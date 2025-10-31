import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer> lbooks = new ArrayList<>();
        List<Integer> rbooks = new ArrayList<>();
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if(n < 0) lbooks.add(Math.abs(n));
            else rbooks.add(n);
        }

        Collections.sort(lbooks, Collections.reverseOrder());
        Collections.sort(rbooks, Collections.reverseOrder());

        int answer = 0;
        for (int i = 0; i < lbooks.size(); i += M) answer += lbooks.get(i) * 2;
        for (int i = 0; i < rbooks.size(); i += M) answer += rbooks.get(i) * 2;

        // 가장 마지막에 가장 먼 곳으로 갈 때는 다시 돌아올 필요가 없음
        int maxDist = 0;
        if(!lbooks.isEmpty()) maxDist = Math.max(maxDist, lbooks.get(0));
        if(!rbooks.isEmpty()) maxDist = Math.max(maxDist, rbooks.get(0));

        System.out.println(answer - maxDist);
    }
}