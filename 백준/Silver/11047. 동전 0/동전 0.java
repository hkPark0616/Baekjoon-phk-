import java.util.*;

public class Main {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int k = sc.nextInt();
            ArrayList<Integer> arr = new ArrayList<>();
            for(int i = 0;i < n;i++){
                arr.add(sc.nextInt());
            }
            int result = 0;

            for(int i = arr.size()-1;i >= 0;i--){
                if(arr.get(i) <= k){
                    result = result + k/arr.get(i);
                    k = k%arr.get(i);
                }
            }


            System.out.println(result);


    }

}
