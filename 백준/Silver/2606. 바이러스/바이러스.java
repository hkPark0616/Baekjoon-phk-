import java.util.*;

public class Main {
    public static ArrayList<Integer>[] list;
    public static boolean[] visit;
    public static int count = 0;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int com = sc.nextInt();
        int net = sc.nextInt();

        list = new ArrayList[com + 1];
        visit = new boolean[com + 1];

        for(int i = 1;i <= com;i++){
            list[i] = new ArrayList<Integer>();
        }

        for(int i = 0;i < net;i++){
            int fi = sc.nextInt();
            int se = sc.nextInt();
            list[fi].add(se);
            list[se].add(fi);

        }

        System.out.println(dfs(1));

    }

    public static int dfs(int i){

        visit[i] = true;
        for(int k : list[i]){
            if(visit[k] == false){
                count++;
                dfs(k);
            }
        }
        return count;

    }

}
