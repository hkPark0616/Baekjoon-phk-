import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int arr[][] = new int[N][2];


        for(int i = 0;i < N;i++){
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }


        for(int i = 0;i<N;i++){
            int count = 1;
            for(int j = 0;j < N;j++){
                if(i==j){
                    continue;
                }
                if(arr[i][0] < arr[j][0] && arr[i][1] < arr[j][1]){
                    count++;
                }
            }
            System.out.println(count);
        }



    }

}
