import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = br.readLine();
        int temp;
        int arr[] = new int[N.length()];

        for(int i = 0;i < arr.length;i++){
            arr[i] = Integer.parseInt(String.valueOf(N.charAt(i)));
        }
        for(int i = 0;i < arr.length;i++){
            for(int j = i+1;j < arr.length;j++){
                if(arr[i] < arr[j]){
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }

        }
        for(int i = 0;i < arr.length;i++){
            System.out.print(arr[i]);
        }

    }

}
