import java.util.*;

public class Main {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        String num = sc.next();

        Character arr[] = new Character[num.length()];
        StringBuilder sb = new StringBuilder();

        int result = 0;

        for(int i = 0;i < num.length();i++){
            //arr[i] = Integer.parseInt(String.valueOf(num.charAt(i)));
            arr[i] = num.charAt(i);
        }

        Arrays.sort(arr);



        for(int i = arr.length - 1; i >= 0; i--){
            int number = arr[i] - '0';
            result += number;
            sb.append(number);
        }

        if(arr[0] != '0' || result % 3 != 0){
            System.out.println(-1);
        }
        else {
            System.out.println(sb);
        }



    }

}