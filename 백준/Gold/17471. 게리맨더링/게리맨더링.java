import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] population;
    static List<List<Integer>> graph;
    static int[] arr; // 조합 배열
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 구역의 개수
        N = Integer.parseInt(st.nextToken());

        // 각 구역의 인구
        st = new StringTokenizer(br.readLine());
        population = new int[N + 1];
        for(int i = 1; i <= N; i++){
            population[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList<>();
        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken()); // 연결된 구역 수
            for (int j = 0; j < count; j++) {
                graph.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }


        arr = new int[N + 1];
        combination(1, 0);

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);

    }

    static void combination(int start, int cnt){
        // 적어도 한 개 이상의 구역이 포함되어야 하고
        // 두 그룹으로 나누어야 하므로 절반까지만
        if (cnt > 0 && cnt <= N / 2) {
            checkGroup();
        }

        for(int i = start; i <= N; i++){
            arr[i] = 1; // 그룹 A에 포함
            combination(i + 1, cnt + 1);
            arr[i] = 0; // 포함한거 다시 되돌림
        }

    }

    static void checkGroup(){
        List<Integer> groupA = new ArrayList<>();
        List<Integer> groupB = new ArrayList<>();

        // 두 그룹을 분리(1 : A, 0 : B)
        for(int i = 1; i <= N; i++){
            if(arr[i] == 1) {
                groupA.add(i);
            } else {
                groupB.add(i);
            }
        }

        // 두 그룹 모두 연결되어 있으면
        if(bfs(groupA) && bfs(groupB)){
            int sumA = 0;
            int sumB = 0;

            for(int i: groupA){
                sumA += population[i];
            }

            for(int i: groupB){
                sumB += population[i];
            }

            int groupDiff = Math.abs(sumA - sumB);

            min = Math.min(min, groupDiff);
        }
    }
    
    // 그룹이 연결되었는지
    static boolean bfs(List<Integer> group){
        boolean[] visited = new boolean[N + 1];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        
        // 구역의 첫 번째 지역을 시작점으로 설정
        int start = group.get(0);

        q.offer(start);
        visited[start] = true;

        int cnt = 0;

        while(!q.isEmpty()){
            int cur = q.poll();
            
            // 방문한 구역 카운트
            cnt++;
            
            // 연결된 지역을 확인함
            List<Integer> list = graph.get(cur);
            for(int near: list){
                // 아직 방문하지 않았고, 같은 그룹에 속하는지
                if(!visited[near] && group.contains(near)){
                    visited[near] = true;
                    q.offer(near);
                }
            }
        }

        // 그룹 내 모든 구역을 모두 돌았나?
        return cnt == group.size();
    }
}