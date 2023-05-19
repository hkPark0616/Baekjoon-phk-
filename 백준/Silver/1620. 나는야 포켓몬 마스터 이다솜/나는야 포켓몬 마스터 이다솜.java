import java.util.HashMap;
import java.util.Scanner;

public class Main {
        public static boolean isNumeric(String s) {
            try {
                Double.parseDouble(s);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int m = sc.nextInt();

            StringBuilder sb = new StringBuilder();

            HashMap<Integer, String> hm = new HashMap<>();
            HashMap<String, Integer> hm2 = new HashMap<>();

            for(int i =1;i < n + 1;i++){
                String s = sc.next();
                hm.put(i, s);
                hm2.put(s, i);
            }

            for(int i = 0;i < m;i++){
                String in = sc.next();
                if(isNumeric(in)){
                    sb.append(hm.get(Integer.parseInt(in))).append("\n");
                }else{
                    sb.append(hm2.get(in)).append("\n");
                }

            }

            System.out.println(sb);
    }
}
