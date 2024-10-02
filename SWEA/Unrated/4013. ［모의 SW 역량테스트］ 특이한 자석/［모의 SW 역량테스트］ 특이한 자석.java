import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static int K, num, direction;
    static List<Integer>[] gears = new ArrayList[4];
    static int[] gear_dir;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            gears = new ArrayList[4];
            gear_dir = new int[4];

            for(int i = 0; i < 4; i++){
                gears[i] = new ArrayList<>();
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 8; j++){
                    gears[i].add(Integer.parseInt(st.nextToken()));
                }
            }


            for(int i = 0; i < K; i++){
                st = new StringTokenizer(br.readLine());
                num = Integer.parseInt(st.nextToken()) - 1;
                direction = Integer.parseInt(st.nextToken());

                Arrays.fill(gear_dir, 0);
                gear_dir[num] = direction;

                left(num);
                right(num);

                for(int j = 0; j < 4; j++){
                    if(gear_dir[j] == -1){
                        turnLeft(j);
                    }else if(gear_dir[j] == 1){
                        turnRight(j);
                    }
                }
            }

            int answer = 0;
            for(int i = 0; i < 4; i++){
                answer += gears[i].get(0) * (int)Math.pow(2, i);
            }

            System.out.println("#" + t + " " + answer);
        }
    }

    static void left(int num){
        if(num == 0) return;

        if(gears[num].get(6) != gears[num - 1].get(2)){
            if(gear_dir[num] == 1){
                gear_dir[num - 1] = -1;
            }else if(gear_dir[num] == -1) {
                gear_dir[num - 1] = 1;
            }
        }

        left(num - 1);
    }

    static void right(int num){
        if(num == 3) return;

        if(gears[num].get(2) != gears[num + 1].get(6)){
            if(gear_dir[num] == 1){
                gear_dir[num + 1] = -1;
            }else if(gear_dir[num] == -1) {
                gear_dir[num + 1] = 1;
            }
        }

        right(num + 1);
    }

    static void turnRight(int num){
       gears[num].add(0, gears[num].get(7));
       gears[num].remove(8);
    }

    static void turnLeft(int num){
        gears[num].add(gears[num].get(0));
        gears[num].remove(0);
    }
}
