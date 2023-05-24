import java.util.*;


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int count = 0;
        int a = 1;
        int num = 10;
        for(int i = 1;i <= n;i++){
            if(i % num == 0){
                a++;
                num *= 10;
            }
            count += a;

        }

        System.out.println(count);
    }
}
