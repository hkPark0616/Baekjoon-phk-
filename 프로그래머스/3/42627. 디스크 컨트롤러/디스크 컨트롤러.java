import java.util.*;

class Solution {
    static class Job implements Comparable<Job> {
        int num, start, end, time;
        
        public Job(int num, int start, int end, int time) {
            this.num = num;
            this.start = start;
            this.end = end;
            this.time = time;
        }
        
        @Override
        public int compareTo(Job o) {
            if(this.time != o.time) return Integer.compare(this.time, o.time);
            if(this.start != o.start) return Integer.compare(this.start, o.start);
            return Integer.compare(this.num, o.num);
        }
    }
    
    public int solution(int[][] jobs) {
        int answer = 0;
        
        List<Job> list = new ArrayList<>();
        for(int i = 0; i < jobs.length; i++) {
            int s = jobs[i][0]; // 작업 요청 시점
            int l = jobs[i][1]; // 작업 소요 시간
            
            list.add(new Job(i, s, 0, l));
        }
        
        list.sort(Comparator.comparingInt(job -> job.start)); // 시작 시간 오름차순
        
        PriorityQueue<Job> pq = new PriorityQueue<>();
        int done = 0; // 완료한 작업 수
        int time = 0; // 현재 시간
        int idx = 0; // 작업 idx
        
        while(done < jobs.length) {
            // 현재 시간까지 도착한 작업 시작
            while(idx < jobs.length && list.get(idx).start <= time) {
                pq.offer(list.get(idx));
                idx++;
            }
            
            // 아직 도착한 작업 없으면 다음 작업 시작 시간으로 이동
            if(pq.isEmpty()) {
                time = list.get(idx).start;
                continue;
            }
            
            Job curJob = pq.poll();
            time += curJob.time; // 작업 처리 시간 추가
            answer += time - curJob.start; // 반환시간
            done++; // 작업 완료
        }
        
        return answer / jobs.length;
    }
}