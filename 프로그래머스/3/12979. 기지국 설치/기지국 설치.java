class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        
        int coverage = w * 2 + 1;
        int start = 1;
        int idx = 0;
        
        while(start <= n) {
            // 기지국 범위 내에 있으면
            if(idx < stations.length && stations[idx] - w <= start) {
                start = stations[idx] + w + 1; // 다시 해당 기지국 범위 밖으로 시작점 변경
                idx++;
            } else {
                // 기지국 설치할 수 있는 위치면 설치하고 시작점 변경
                answer++;
                start += coverage;
            }
        }

        return answer;
    }
}