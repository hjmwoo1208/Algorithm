import java.util.*;
public class 네트워크 {
    public static int solution(int n, int[][] computers) {
        int answer = 0;
        boolean visited[] =  new boolean[n];
        for(int i=0;i<n;i++) {
        	if(visited[i]) continue;
        	Queue<Integer> q =  new LinkedList<Integer>();
        	q.offer(i);
        	visited[i] = true;
        	while(!q.isEmpty()) {
        		int out = q.poll();
        		for(int j=0;j<n;j++)
        			if(j!=out) {
        				if(computers[out][j]==1 && !visited[j]) {
        					q.offer(j);
        					visited[j] = true;
        				}
        			}
        	}
        	answer++;
        }
        return answer;
    }
	public static void main(String[] args) {
		System.out.println(solution(3, new int[][] {{1, 1, 0},{1, 1, 1},{0,1,1}}));
	}
}
