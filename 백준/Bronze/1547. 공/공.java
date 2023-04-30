import java.util.Scanner;


public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int [] arr = {0, 1, 0, 0};
        for(int i=0;i<M;i++){
            int first = sc.nextInt();
            int second = sc.nextInt();
            int temp = arr[first];
            arr[first] = arr[second];
            arr[second] = temp;
        }

        for (int i = 1;i<arr.length;i++){
            if (arr[i]==1){
                System.out.println(i);
                break;
            }
        }

    }
}