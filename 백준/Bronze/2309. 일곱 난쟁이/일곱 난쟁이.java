import java.util.*;


public class Main {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Integer> arr = new ArrayList<Integer>();
        int sum = 0;
        for(int i = 0;i < 9;i++){
            int a = sc.nextInt();
            sum +=a;
            arr.add(a);
        }

        for(int i = 0;i < 9;i++){
            for(int j =0;j < i;j++){
                if(sum - (arr.get(i) + arr.get(j)) == 100){
                    arr.remove(i);
                    arr.remove(j);
                    break;
                }
            }
            if(arr.size() == 7)break;
        }
        Collections.sort(arr);

        for(int i = 0;i < 7;i++){
            System.out.println(arr.get(i));
        }


    }
}