import java.util.*;
import java.io.*;
/*
 * 백준 1167 - 최소 스패닝 트리
 * - 크루즈칼 방식
 */
public class BOJ_1167_Kruskal {
	static class Edge implements Comparable<Edge>{
		int start, end, weight;

		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge e) {
			return this.weight - e.weight;
		}
	}
	
	// 부모 노드 저장 
	static int parent[] = new int[10002];
	
	// 부모 노드 찾는 메소드
	static int getParent(int x) {
		if(parent[x] == x) return x;
		return parent[x] = getParent(parent[x]);
	}
	
	// 노드 연결 메소드
	static void unionParent(int a, int b) {
		int A = getParent(a);
		int B = getParent(b);
		if(A<B) parent[B] = A;
		else parent[A] = B;
	}
	
	// 사이클 확인 메소드
	static boolean isCycle(int x, int y) {
		int X = getParent(x);
		int Y = getParent(y);
		if(X == Y) return true;
		return false;
	}
	
	// 부모 노드 행렬 초기화
	static void init(int size) {
		for(int i=0;i<size;i++) parent[i] = i;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(stz.nextToken());
		int E = Integer.parseInt(stz.nextToken());
		
		// 부모 노드 초기화 
		init(10002);
		
		// 가중치 오름차순으로 노드 저장 
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		for(int i=0;i<E;i++) {
			stz = new StringTokenizer(br.readLine());
			pq.offer(new Edge(Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken())));
		}
		
		long SUM = 0; // 가중치 합
		int edgeCount = 0; // 연결된 간선의 수

		while(edgeCount<V-1) { // 최소로 연결되는 간선의 수는 (정점-1)
			Edge e = pq.poll();
			// 연결되는 두 정점이 사이클이 아니라면
			if(!isCycle(e.start, e.end)) {
				// 두 노드를 연결
				unionParent(e.start,e.end);
				// 가중치를 더함
				SUM += e.weight;
				// 연결된 간선의 수 증가
				edgeCount++;
			}
		}
		System.out.println(SUM);

	}
}