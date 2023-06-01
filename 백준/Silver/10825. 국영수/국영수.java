import java.util.*;

public class Main {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String arr[][] = new String[n][4];
        for(int i = 0;i < n;i++){
            arr[i][0] = sc.next();
            arr[i][1] = sc.next();
            arr[i][2] = sc.next();
            arr[i][3] = sc.next();
        }

        Arrays.sort(arr, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {

                if(Integer.parseInt(o1[1]) != Integer.parseInt(o2[1])){
                    return Integer.parseInt(o2[1]) - Integer.parseInt(o1[1]);
                }
                else if(Integer.parseInt(o1[2]) != Integer.parseInt(o2[2])){
                    return Integer.parseInt(o1[2]) - Integer.parseInt(o2[2]);
                }
                else if(Integer.parseInt(o1[3]) != Integer.parseInt(o2[3])){
                    return Integer.parseInt(o2[3]) - Integer.parseInt(o1[3]);
                }else{
                    return o1[0].compareTo(o2[0]);
                }

                //return 0;
            }
        });

        for(int i = 0;i < n;i++){
            System.out.println(arr[i][0]);
        }



    }

}