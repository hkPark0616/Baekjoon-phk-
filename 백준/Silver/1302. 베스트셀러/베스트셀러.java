import java.util.*;

public class Main {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            HashMap<String, Integer> map = new HashMap<>();

            int n = sc.nextInt();
            int max = 0;
            for(int i = 0;i < n;i++){

                String text = sc.next();
                if(map.containsKey(text)){
                    map.put(text, map.get(text) + 1);
                }
                else{
                    map.put(text, 1);
                }

            }
            String result = "";
            for(String key : map.keySet()){
                int value = map.get(key);

                if(value > max){
                    max = value;
                    result = key;
                }else if(max == value && result.compareTo(key) > 0){
                    result = key;
                }
            }
            System.out.println(result);



    }

}
