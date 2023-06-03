import java.util.*;

public class Main {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int[] d = new int[x + 1];
        d[1] = 0;

       for(int i = 2;i <= x;i++){

            d[i] = d[i-1] + 1;

            if(i % 2 == 0) {
                d[i] = Math.min(d[i], d[i/2] + 1);

            }
            if (i % 3 == 0){
                d[i] = Math.min(d[i], d[i/3] + 1);

            }
        }
        System.out.println(d[x]);
    }
}
