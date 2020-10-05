import java.util.*;
import java.io.*;
public class BOJ_16398 {
	static class planet implements Comparable<planet>{
		int start, end, cost;
		public planet(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
		@Override
		public int compareTo(planet o) {
			return this.cost-o.cost;
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
		long sum =0;
		int flow=0;
		PriorityQueue<planet> q = new PriorityQueue<planet>();
		boolean check[] = new boolean[N];
		check[0]=true;
		for(int i=1;i<N;i++) q.offer(new planet(0, i, C[0][i]));
		while(flow<N-1) {
			planet out = q.poll();
			if(check[out.end] == true) continue;
			check[out.end] = true;
			flow++;
			sum+=out.cost;
			for(int i=0;i<N;i++)
				if(i!=out.end && check[i]==false) q.offer(new planet(out.end, i, C[out.end][i]));
		}
		System.out.println(sum);
	}
}