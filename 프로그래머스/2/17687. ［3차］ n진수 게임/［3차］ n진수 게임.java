class Solution {
    static int N;
    
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        N = n;
        
        int turn = 1; // 현재 차례
        int num = 0;  // 변환할 숫자
        int cnt = 0;  // 선택한 숫자의 개수
        
        while (cnt < t) {
            String convertString = convert(num++);
            for (int i = 0; i < convertString.length(); i++) {
                if (p == turn) {
                    answer.append(convertString.charAt(i));
                    cnt++;
                    if (cnt >= t) break;
                }
                turn++;
                
                if (turn > m) turn = 1;   
            }
        }
        
        return answer.toString();
    }
    
    public String convert(int num) {
        StringBuilder s = new StringBuilder();
        
        // n진수로 변환
        do {
            int temp = num % N;
            if (temp >= 10) {
                s.append((char) ('A' + (temp - 10))); // A-F 변환
            } else {
                s.append(temp);
            }
            num /= N;
        } while (num > 0);
        
        return s.reverse().toString(); // 역순
    }
}
