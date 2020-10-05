/* < Prim 알고리즘 > * * * * * * * * * * * * * * * * * * * 
 *  - 임의의 정점을 중심으로 뻗어나가는 알고리즘
 *  - 최선의 간선을 찾아서 뻗어나간다.
 *  - 연결된 정점에서 갈 수 있는 최소 비용을 찾아가며 연결한다.
 *  - 연결된 간선의 갯수 = 정점의 갯수 -1
 *  - 간선이 많을 때는 Prim알고리즘이 적절하다. 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
import java.util.*;
import java.io.*;
public class BOJ_16398 {
	static class planet implements Comparable<planet>{ //행성의 정보를 저장하기 위한 클래스
		int start, end, cost;
		public planet(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
		@Override
		public int compareTo(planet o) {
			return this.cost-o.cost; //플로우 관리비용을 오름차순으로 정렬한다.
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int C[][] = new int[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer stz = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) C[i][j] = Integer.parseInt(stz.nextToken());
		}
		long sum =0; //총 플로우 관리비용
		int flow=0; //연결된 플로우의 갯수
		PriorityQueue<planet> q = new PriorityQueue<planet>(); //플로우 관리 비용을 기준으로 우선 순위 큐
		boolean check[] = new boolean[N]; //방문 여부 체크
		check[0]=true; //0번 행성을 시작으로 플로우를 이어나간다.
		for(int i=1;i<N;i++) q.offer(new planet(0, i, C[0][i]));
		while(flow<N-1) { //연결된 플로우의 갯수 = N-1
			planet out = q.poll();
			if(check[out.end] == true) continue; //이미 연결된 행성이면 지나간다.
			check[out.end] = true; //out.end 행성에 방문 체크
			flow++; //연결된 플로우 수 증가
			sum+=out.cost; //연결된 플로우의 관리비용 저장
			for(int i=0;i<N;i++) //새로 연결된 행성과 연결된 다른 행성들의 정보를 우선 순위 큐에 넣는다. 자기 자신과 이미 연결된 행성은 넣지않는다.
				if(i!=out.end && check[i]==false) q.offer(new planet(out.end, i, C[out.end][i]));
		}
		System.out.println(sum);
	}
}