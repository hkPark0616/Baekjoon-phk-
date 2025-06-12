import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        // 차량의 진출 시점을 기준으로 오름차순 정렬
        Arrays.sort(routes, (o1, o2) -> {
            // 진출 시점이 같다면 진입 시점 기준으로 정렬
            if(o1[1] == o2[1])
                return o1[0] - o2[0];
            return o1[1] - o2[1];
        });
        
        int camPos = Integer.MIN_VALUE;
        
        // 현재 카메라가 해당 차량의 시작 지점보다 앞에 있다면, 
        // 단속 불가능 → 새로운 카메라를 그 차량의 진출 지점에 설치
        for(int[] route: routes) {
            int start = route[0];
            int end = route[1];
            
            if(camPos < start) {
                camPos = end;
                answer++;
            }
        }
        
        return answer;
    }
}