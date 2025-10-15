class Solution {
    public int solution(int n) {
        int answer = 0;
        
        if(n % 2 != 0) return 0;
        
        long[] dp = new long[n + 1];
        
        dp[0] = 1;
        dp[2] = 3;
        for(int i = 4; i <= n; i += 2) {
            dp[i] = (dp[i - 2] * 3) % 1000000007; // 기본 3가지
            for(int j = 4; j <= i; j += 2) { // 특수패턴 2가지 짝수 단위로 등장
                dp[i] = (dp[i] + (dp[i - j] * 2) % 1000000007) % 1000000007;
            }
        }
        
        answer = (int) dp[n];
        
        return answer;
    }
}