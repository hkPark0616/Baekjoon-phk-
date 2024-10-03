import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;

        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++) {
            queue.offer(new Node(priorities[i], i));
        }

        Arrays.sort(priorities);
        int maxPriorityIndex = priorities.length - 1;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.value < priorities[maxPriorityIndex]) {
                queue.offer(current);
            } else {
                answer++;
                maxPriorityIndex--;

                if (current.index == location) {
                    return answer;
                }
            }
        }

        return answer;
    }
}

class Node {
    int value;
    int index;

    public Node(int value, int index) {
        this.value = value;
        this.index = index;
    }
}
