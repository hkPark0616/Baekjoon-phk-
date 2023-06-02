import java.util.*;

public class Main {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for(int i = 0;i < t;i++){
            int n = sc.nextInt();
            StringBuilder sb = new StringBuilder();
            Set<Integer> set = new HashSet<>();

            for(int j = 0;j < n;j++){
                set.add(sc.nextInt());
            }

            int m = sc.nextInt();

            for(int j = 0;j < m;j++){
                int num = sc.nextInt();
                if(set.contains(num)){
                    sb.append(1).append("\n");
                }else{
                    sb.append(0).append("\n");
                }
            }
            System.out.print(sb);
        }
    }
}