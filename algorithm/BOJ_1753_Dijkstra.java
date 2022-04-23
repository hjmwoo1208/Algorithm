import java.util.*;
import java.io.*;
/**
 * [백준] 1753 - 최단 경로
 * @author WOOHJ
 * - 다익스트라 알고리즘
 */
public class BOJ_1753_Dijkstra {
	static class Node implements Comparable<Node>{
		int end, weight;

		public Node(int end, int weight) {
			super();
			this.end = end;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node n) {
			return this.weight - n.weight;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(stz.nextToken());
		int E = Integer.parseInt(stz.nextToken());
		
		int start = Integer.parseInt(br.readLine());

		// 가중치 저장
		List<int[]> weight[] = new LinkedList[V+2];
		for(int i=1;i<=V;i++) weight[i] = new LinkedList<int[]>();
		int dist[] = new int[V+2];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		for(int i=0;i<E;i++) {
			stz = new StringTokenizer(br.readLine());
			weight[Integer.parseInt(stz.nextToken())].add(new int[] {Integer.parseInt(stz.nextToken()),Integer.parseInt(stz.nextToken())});
		}
		
		// 이미 방문이 끝난 정점
		boolean visited[] = new boolean[V+2];
		visited[start] = true;
		dist[start] = 0;
		
		// 가중치 오름차순
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		for(int[] n : weight[start]) {
			dist[n[0]] = dist[n[0]] > dist[start] + n[1] ? dist[start] + n[1] : dist[n[0]];
			pq.offer(new Node(n[0],dist[n[0]]));
		}

		while(!pq.isEmpty()) {
			Node n = pq.poll();
			if(visited[n.end]) continue;
			visited[n.end] = true;
			for(int[] next : weight[n.end]) {
				dist[next[0]] = dist[next[0]] > dist[n.end] + next[1] ? dist[n.end] + next[1] : dist[next[0]];
				pq.offer(new Node(next[0],dist[next[0]]));
			}
		}
		for(int i=1;i<=V;i++) System.out.println(dist[i] == Integer.MAX_VALUE? "INF" : dist[i]);
	}
}