class Solution {
    
    public int GCD(int n, int m){
        int a = n;
        int b = m;
        while(b > 0){
            int mod = a;
            a = b;
            b = mod % a;
        }
        return a;
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