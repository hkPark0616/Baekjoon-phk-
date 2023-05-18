import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int cnt = 0;

        for(int i = 0;i < n;i++){

            String input = sc.next();
            for(int j = 0;j < input.length();j++){
                if(input.charAt(j) == '('){
                    cnt++;
                } else if (input.charAt(j) == ')') {
                    cnt--;
                    if(cnt < 0) break;
                }
            }

            if(cnt == 0){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }

            cnt = 0;
        }
    }
}
