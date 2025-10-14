import java.util.*;

class Solution {
    Map<String, List<Song>> map = new HashMap<>(); // 장르별 노래 목록
    Map<String, Integer> totalPlays = new HashMap<>(); // 장르별 재생 수
    class Song implements Comparable<Song> {
        int n, c;
        
        public Song(int n, int c) {
            this.n = n;
            this.c = c;
        }
        
        @Override
        public int compareTo(Song o) {
            if(this.c == o.c) return Integer.compare(this.n, o.n);
            return Integer.compare(o.c, this.c);
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        int[] answer;
        
        for(int i = 0; i < genres.length; i++) {
            map.putIfAbsent(genres[i], new ArrayList<>());
            map.get(genres[i]).add(new Song(i, plays[i]));
            totalPlays.put(genres[i], totalPlays.getOrDefault(genres[i], 0) + plays[i]);
        }
        
        // 장르 내에서 많이 재생된 노래 정렬
        List<String> order = new ArrayList<>(totalPlays.keySet());
        order.sort((a, b) -> totalPlays.get(b) - totalPlays.get(a));
        
        List<Integer> results = new ArrayList<>();
        for(String g: order) {
            List<Song> songs = map.get(g);
            Collections.sort(songs);
            for(int i = 0; i < songs.size() && i < 2; i++) {
                results.add(songs.get(i).n);
            }
        }
        
        answer = results.stream().mapToInt(i -> i).toArray();
        
        return answer;
    }
}