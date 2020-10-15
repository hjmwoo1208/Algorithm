import java.util.*;
import java.io.*;
public class BOJ_4485 {
	static class Rupee implements Comparable<Rupee>{
		int y, x, lost;
		public Rupee(int y, int x, int lost) {
			super();
			this.y = y;
			this.x = x;
			this.lost = lost;
		}
		@Override
		public int compareTo(Rupee o) {
			return this.lost-o.lost;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = 1;
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N == 0) break; //게임 종료
			int map[][] = new int[N][N], MIN=0, py[] = {-1,0,1,0}, px[] = {0,1,0,-1};
			for(int i=0;i<N;i++) {
				StringTokenizer stz = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) map[i][j] = Integer.parseInt(stz.nextToken());
			}
			boolean visited[][] = new boolean[N][N];
			PriorityQueue<Rupee> q = new PriorityQueue<Rupee>(); //잃은 루피가 작은 순으로 우선순위큐
			visited[0][0]	= true;
			q.offer(new Rupee(0, 0, map[0][0]));
			while(!(q.isEmpty())) {
				Rupee out = q.poll();
				if(out.y==N-1 && out.x==N-1) { //처음 도착하는 루트가 제일 적게 잃은 코스
					MIN = out.lost;
					break;
				}
				for(int p=0;p<4;p++) {
					int y = out.y +py[p];
					int x = out.x +px[p];
					if(y<0||x<0||y>N-1||x>N-1||visited[y][x]) continue; //경계를 넘어가거나, 방문하면 보지 않는다. --> 이미 방문한곳 = 현재 루트가 더 많이 잃는 코스
					q.offer(new Rupee(y, x, out.lost+map[y][x]));
					visited[y][x] = true;
				}
			}
			System.out.println("Problem "+(tc++)+": "+MIN); //문제 번호 출력후 tc증가
		}
	}//main
}