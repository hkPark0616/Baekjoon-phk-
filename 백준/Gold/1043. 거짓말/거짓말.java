import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int[] parent;
    static boolean[] knowns;
    static List<List<Integer>> parties = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for(int i = 0; i <= N; i++) parent[i] = i;

        st = new StringTokenizer(br.readLine());
        int known = Integer.parseInt(st.nextToken());

        knowns = new boolean[N + 1];
        for(int i = 0; i < known; i++) {
            int n = Integer.parseInt(st.nextToken());
            knowns[n] = true;
        }

        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            List<Integer> party = new ArrayList<>();
            int first = -1;

            // 같은 파티에 있는 사람 그룹으로 묶
            for(int i = 0; i < n; i++) {
                int num = Integer.parseInt(st.nextToken());
                party.add(num);

                if(i == 0) first = num;
                else union(first, num);
            }

            parties.add(party);
        }

        // 진실을 아는 사람들의 그룹 대표(parent)를 저장해 둔다
        Set<Integer> truthKnownPartis = new HashSet<>();
        for(int i = 1; i <= N; i++) {
            if(knowns[i]) truthKnownPartis.add(find(i));
        }

        // 각 파티에 진실을 아는 사람이 포함되었는지 확인
        int answer = 0;
        for(List<Integer> party: parties) {
            boolean canLie = true;
        
            for(int p: party) {
                // 진실을 아는 사람 그룹에 포함되어 있으면
                if(truthKnownPartis.contains(find(p))) {
                    canLie = false;
                    break;
                }
            }

            if(canLie) answer++;
        }

        System.out.println(answer);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) {
            parent[b] = a;
        }
    }

    static int find(int a) {
        if(a == parent[a]) return parent[a];

        return parent[a] = find(parent[a]);
    }

    static boolean isSame(int a, int b) {
        return find(a) == find(b);
    }
}