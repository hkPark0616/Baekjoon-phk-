import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        char arr[][] = new char[n][m];

        int res = 0;
        int res2 = 0;
        for(int i=0; i<arr.length; i++) {
            String str = sc.next();
            for(int j=0; j<arr[i].length; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        for(int i=0; i<n; i++) {
            boolean count = true;
            for(int j=0; j<m; j++) {
                if(arr[i][j] == 'X' ){
                    count = false;
                    break;
                }

            }
            if(count){
                res+=1;
            }
        }
        for(int i=0; i<m; i++) {
            boolean count = true;
            for(int j=0; j<n; j++) {
                if(arr[j][i] == 'X' ){
                    count = false;
                    break;
                }

            }
            if(count){
                res2+=1;
            }
        }

        if(res > res2){
            System.out.println(res);
        }else {
            System.out.println(res2);
        }




    }
}