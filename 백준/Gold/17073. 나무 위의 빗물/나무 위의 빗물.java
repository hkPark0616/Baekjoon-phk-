import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static double W;
    static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        W = Double.parseDouble(st.nextToken());

        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        
        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            graph.get(U).add(V);
            graph.get(V).add(U);
        }

        // 루트 제외, 연결된 노드가 1개인 노드 -> 리프노드
        int count = 0;
        for(int i = 2; i <= N; i++) {
            if(graph.get(i).size() == 1) count++;
        }

        // 모든 물은 결국 리프에서만 멈추므로, Pi > 0인 정점은 리프밖에 없음
        // -> Pi들의 평균은 W / 리프의 개수
        System.out.println(W / count);
    }
}