class Solution {
    
    public int GCD(int n, int m){
        while(m > 0){
            int mod = n;
            n = m;
            m = mod % n;
        }
        return n;
    }
    public int[] solution(int n, int m) {
        int[] answer = new int[2];
        int gcd = 0;
        int lcm = 0;
        
        gcd = GCD(n, m);
        lcm = (n * m) / gcd;
        
        answer[0] = gcd;
        answer[1] = lcm;
        
        return answer;
    }
}