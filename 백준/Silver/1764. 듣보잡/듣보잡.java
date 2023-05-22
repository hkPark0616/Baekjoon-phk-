import java.util.*;

public class Main {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            HashSet<String> set = new HashSet<>();
            ArrayList<String> list = new ArrayList<>();

            int n = sc.nextInt();
            int m = sc.nextInt();
            for(int i = 0;i < n;i++){
                set.add(sc.next());
            }
            int count = 0;
            for(int i = 0;i < m;i++){
                String name = sc.next();

                if(set.contains(name)){
                    count++;
                    list.add(name);

                }
            }

            Collections.sort(list);

            System.out.println(count);
            for(String s : list){
                System.out.println(s);
            }

    }

}
