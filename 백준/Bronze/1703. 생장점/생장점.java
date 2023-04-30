import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            if (a==0) break;

            int tree = 1;
            for (int i = 0;i<a;i++){
                int n = Integer.parseInt(st.nextToken());
                int g = Integer.parseInt(st.nextToken());
                tree = tree * n - g;
            }
            System.out.println(tree);


        }

    }
}