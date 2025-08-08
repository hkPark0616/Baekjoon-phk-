import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int max = 0;
        for(int i = 0; i < stones.length; i++) max = Math.max(max, stones[i]);
        
        int answer = search(stones, k, max);
        
        return answer;
    }
    
    static int search(int[] stones, int k, int max) {
        int result = 0;
        int left = 0;
        int right = max;
        
        while(left <= right) {
            int mid = (left + right) / 2;
            int cnt = 0; // 연속으로 못밟는 돌 개수
            boolean no = false;
            
            // mid명이 건널 수 있는지
            for(int i = 0; i < stones.length; i++) {
                // 현재 돌을 mid 명이 건널 수 있는지
                if(stones[i] - mid < 0) {
                    cnt++;
                    
                    if(cnt >= k) {
                        no = true;
                        break;
                    }
                } else { // 못건너면 초기화
                    cnt = 0;
                }
            }
            
            if(no) { // 못건넘
                right = mid - 1;
            } else {
                result = mid;
                left = mid + 1;
            }
        }
        
        return result;
    }
}