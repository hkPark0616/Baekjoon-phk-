import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashMap<String, String> hash = new HashMap<>();
        for(int i = 0;i < n;i++){
            st = new StringTokenizer(br.readLine());
            String id = st.nextToken();
            String passwd = st.nextToken();
            hash.put(id, passwd);
        }

        for(int i = 0;i < m;i++){
            String text = br.readLine();
            System.out.println(hash.get(text));

        }


    }

}