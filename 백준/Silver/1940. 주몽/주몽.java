import java.util.*;

public class Main {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<Integer> li = new ArrayList<>();

        for(int i = 0;i < n;i++){
            li.add(sc.nextInt());
        }
        Collections.sort(li);
        int count = 0;
        for(int i = 0;i < n;i++){
            for(int j = i+1;j < n;j++){
                if(li.get(i) + li.get(j) == m){
                    count++;

                    break;
                }
            }
        }
        System.out.println(count);

    }
}
