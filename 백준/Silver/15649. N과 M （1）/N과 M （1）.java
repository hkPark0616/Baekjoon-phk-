import java.io.IOException;
import java.util.*;

public class Main {
    public static int n, m;
    public static int arr[];
    public static boolean check[];
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();

        n = sc.nextInt();
        m = sc.nextInt();

        arr= new int[9] ;
        check = new boolean[9];

        find(0);
        System.out.println(sb);


    }

    public static void find(int k){
        if(k == m){
            for(int i = 0; i < m;i++){
                sb.append(arr[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 1;i <= n;i++){
            if(!check[i]){
                arr[k] = i;
                check[i] = true;
                find(k + 1);
                check[i] = false;

            }
        }

    }

}