import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int N, map[][], personCnt, min;
    static int arr[];
    static List<int[]> persons, stairs;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            map = new int[N][N];

            personCnt = 0;
            min = Integer.MAX_VALUE;

            persons = new ArrayList<>();
            stairs = new ArrayList<>();

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 1) {
                        persons.add(new int[] {i, j});
                        personCnt++;
                    }else if(map[i][j] == 0) {
                        continue;
                    }else {
                        stairs.add(new int[] {i, j});
                    }
                }
            }

            arr = new int[personCnt];

            stair(0);

            System.out.println("#" + t + " " + min);
        }
    }

    static void stair(int idx) {
        if(idx == persons.size()) {
            int getTime = calc();
            min = Math.min(min, getTime);
            return;
        }

        for(int i = 0; i < 2; i++) {
            arr[idx] = i;
            stair(idx + 1);
        }
    }

    static int calc() {
        List<Integer> stair1 = new ArrayList<>();
        List<Integer> stair2 = new ArrayList<>();

        for(int i = 0; i < personCnt; i++) {
            int[] person = persons.get(i);
            int distance = Math.abs(person[0] - stairs.get(arr[i])[0]) + Math.abs(person[1] - stairs.get(arr[i])[1]);
            distance += 1;
            if(arr[i] == 0) {
                stair1.add(distance);
            }else {
                stair2.add(distance);
            }
        }

        // 가까운 사람부터 계단에 도착함
        Collections.sort(stair1);
        Collections.sort(stair2);

        int stair1Time = getTime(stair1, map[stairs.get(0)[0]][stairs.get(0)[1]]);
        int stair2Time = getTime(stair2, map[stairs.get(1)[0]][stairs.get(1)[1]]);

        int time = Math.max(stair1Time, stair2Time);

        return time;

    }

    static int getTime(List<Integer> group, int stairCapacity){
        int time = 0;
        for(int i = 0; i < group.size(); i++) {
            // 계단에 사람이 3명 이하일때
            if(i - 3 < 0){
                time = group.get(i) + stairCapacity;
            }
            // 계단에 사람이 3명 이상일때
            else{
                if(group.get(i - 3) + stairCapacity <= group.get(i)){
                    time = group.get(i) + stairCapacity;
                }else{
                    time = group.get(i - 3) + stairCapacity + stairCapacity;
                }
            }
        }

        return time;
    }
}
