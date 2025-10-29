import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Integer[] cranes = new Integer[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) cranes[i] = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(br.readLine());
        Integer[] boxes = new Integer[M];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) boxes[i] = Integer.parseInt(st.nextToken());

        // 제일 센 크레인이 제일 무거운 박스를 먼저 들게
        Arrays.sort(cranes, Collections.reverseOrder());
        Arrays.sort(boxes, Collections.reverseOrder());

        if(boxes[0] > cranes[0]) { // 가장 센 크레인이 가장 무거운 박스를 못들면 모두 못옮김
            System.out.println(-1);
            return;
        }
        
        List<Integer> boxList = new ArrayList<>(Arrays.asList(boxes));
        int cnt = 0;
        int time = 0;
        while(!boxList.isEmpty()) {
            int idx = 0;
            for(int crane: cranes) {
                if(idx >= boxList.size()) break;
                while(idx < boxList.size()) {
                    if(crane >= boxList.get(idx)) {
                        boxList.remove(idx);
                        break;
                    }
                    idx++;
                }
            }
            time++;
        }

        System.out.println(time);
    }

}