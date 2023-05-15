import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        boolean arr[] = new boolean[10001];

        for (int i = 1; i < 10001; i++) {
            int n = d(i);

            if( n < 10001){
                arr[n] = true;
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 1;i < 10001;i++){
            if(!arr[i]){
                sb.append(i).append('\n');
            }
        }
        System.out.println(sb);
    }

    public  static int d(int number){
        int sum = number;

        while (number != 0){
            sum = sum + (number%10);
            number = number / 10;
        }

        return  sum;
    }

}
