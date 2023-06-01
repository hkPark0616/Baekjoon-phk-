import java.util.*;

public class Main {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        Set<Integer> A = new HashSet<>();
        Set<Integer> B = new HashSet<>();

        for(int i = 0;i < n;i++){
            A.add(sc.nextInt());
        }

        for(int i = 0;i < m;i++){
            B.add(sc.nextInt());
        }
        int count = 0;
        for(int cnt : A){
            if(!B.contains(cnt)){
                count++;
            }
        }

        for(int cnt : B){
            if(!A.contains(cnt)){
                count++;
            }
        }
        System.out.println(count);
    }

}