import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, mp, mf, ms, mv;
    static int minCost = Integer.MAX_VALUE;
    static int[][] arr;
    static List<Integer> result = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][5];

        st = new StringTokenizer(br.readLine()); // 최소 영양성분
        mp = Integer.parseInt(st.nextToken()); // 단백질
        mf = Integer.parseInt(st.nextToken()); // 지방
        ms = Integer.parseInt(st.nextToken()); // 탄수화물
        mv = Integer.parseInt(st.nextToken()); // 비타민

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0, 0, 0, 0, 0, new ArrayList<>());

        if(minCost == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minCost);
            for(int n: result) System.out.print((n + 1) + " ");
        }
    }

    // 최저 영양소 기준을 만족하는 최소 비용의 식재료 집합
    static void dfs(int idx, int p, int f, int s, int v, int cost, List<Integer> selected) {
        if(idx == N) {
            if (p >= mp && f >= mf && s >= ms && v >= mv) {
                if(cost < minCost) {
                    minCost = cost;
                    result = new ArrayList<>(selected);
                } else if(cost == minCost && compare(selected, result)) {
                    result = new ArrayList<>(selected);
                }
            }
            return;
        }

        selected.add(idx);
        dfs(idx + 1, p + arr[idx][0], f + arr[idx][1], s + arr[idx][2], v + arr[idx][3], cost + arr[idx][4], selected);

        selected.remove(selected.size() - 1);
        dfs(idx + 1, p, f, s, v, cost, selected);
        
    }

    static boolean compare(List<Integer> list1, List<Integer> list2) {
        // 길이가 같을 땐, 수가 작은 쪽
        for (int i = 0; i < Math.min(list1.size(), list2.size()); i++) {
            if (!list1.get(i).equals(list2.get(i))) return list1.get(i) < list2.get(i);
        }
        // 길이가 더 짧은 족
        return list1.size() < list2.size();
    }
}