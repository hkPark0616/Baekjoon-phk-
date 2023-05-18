import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int arrN[] = new int[N+M];

        for(int i = 0;i <N;i++){
            list.add(sc.nextInt());

        }
        for(int i = N;i <M + N;i++){
            list.add(sc.nextInt());

        }
        Collections.sort(list);

       for(int i = 0;i < list.size();i++){
           sb.append(list.get(i)).append('\n');
       }
        System.out.println(sb);
    }
}
