import java.util.*;
import java.io.*;

public class 디스크컨트롤러 {
    static class Node{
    	int start, length ;
		public Node(int start, int length) {
			super();
			this.start = start;
			this.length = length;
		} 
    }
    
	public static int solution(int[][] jobs) {
		int len = jobs.length;
		PriorityQueue<Node> readyQ = new PriorityQueue<Node>(new Comparator<Node>() { //들어오는 job 저장 Queue
			@Override
			public int compare(Node o1, Node o2) { //시작시간 오름차순
				return o1.start - o2.start;
			}
		});
		for(int i=0;i<len;i++) readyQ.offer(new Node(jobs[i][0], jobs[i][1]));
	
		int sum = 0, time = 0, cnt=0; // 작업시간 sum, 현재 시간, 작업 완료된 job
		PriorityQueue<Node> jobQ = new PriorityQueue<Node>(new Comparator<Node>() { //가능 job 대기 Queue
			@Override
			public int compare(Node o1, Node o2) { //작업시간 오름차순
				return o1.length - o2.length;
			}
		});
		while(cnt<len) { //jobs의 갯수 만큼
				while(!readyQ.isEmpty()) { //현재 시간에서 가능한 job 찾기
					if(readyQ.peek().start> time) break;
					jobQ.offer(readyQ.poll());
				}
				if(!jobQ.isEmpty()) { // 현재 시간에 가장 최적 job 실행
					Node out = jobQ.poll();
					int total = time + out.length - out.start;
					time += out.length-1;
					sum += total;
					cnt ++;
				}
				time++;
		}
        return sum/len;
    }
    
	public static void main(String[] args) {
		System.out.println(solution(new int[][] {{0, 3},{1, 9},{2, 6}}));
		System.out.println(solution(new int[][]{{0, 3},{4, 4},{5, 3},{4, 1}})); // 3
		
	}	

}
