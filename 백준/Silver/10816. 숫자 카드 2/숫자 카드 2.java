import java.util.HashMap;
import java.util.Scanner;

public class Main {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            StringBuilder sb = new StringBuilder();
            HashMap<Integer, Integer> hash = new HashMap<>();

            int n = sc.nextInt();

            for(int i = 0;i < n;i++){
                int num = sc.nextInt();

                if(!hash.containsKey(num)){
                    hash.put(num, 1);
                }else{
                    hash.put(num, hash.get(num) + 1);
                }
            }


            int m = sc.nextInt();
            for(int i = 0;i < m;i++){
                int num = sc.nextInt();
                if(hash.containsKey(num)){
                    sb.append(hash.get(num) + " ");
                }else{
                    sb.append(0 + " ");
                }
            }
            System.out.println(sb);
    }

}
