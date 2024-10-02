import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> result = new ArrayList<>();
        
        // 작업별 완료일까지 걸리는 시간 계산
        int[] days = new int[progresses.length];
        for(int i = 0; i < progresses.length; i++) {
            days[i] = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
        }

        // 한 번에 몇 개의 작업이 배포되는지 계산
        int com = 1;
        int currentMax = days[0];
        for(int i = 1; i < days.length; i++) {
            if (days[i] > currentMax) {
                result.add(com);
                com = 1;
                currentMax = days[i];
            } else {
                com++;
            }
        }
        result.add(com);

        // 결과를 배열로 변환
        return result.stream().mapToInt(i -> i).toArray();
    }
}
