import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();


        for(int i = 0;i < t;i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int r = 1;
            for (int j = 0;j < b;j++){
                r = (r * a) % 10;
            }
            if (r == 0){
                System.out.println(10);
            }else{
                System.out.println(r);
            }

        }




    }

}