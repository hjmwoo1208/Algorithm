import java.util.*;
import java.io.*;
public class BOJ_17142 {
	static int MIN = Integer.MAX_VALUE; //최소시간
	static void bfs(int start, int cnt,int N, int M, int set[], int[][] map, List<int[]> virus,int zero) {
		if(cnt == M) {
			int py[] = {-1,0,1,0}, px[] = {0,1,0,-1};
			Queue<int[]> q = new LinkedList<int[]>(); //활성 바이러스 Queue
			boolean check[][] = new boolean[N][N];
			for(int i=0;i<M;i++) {
				q.offer(new int[] {virus.get(set[i])[0],virus.get(set[i])[1]});
				check[virus.get(set[i])[0]][virus.get(set[i])[1]] = true;
			}
			int time = 0;
			while(!(q.isEmpty())) {
				if(zero==0 ) { //빈칸이 없다면
					if(MIN>time) MIN = time; //최소시간 갱신
					return;
				}
				if(MIN<time) return; //가지치기
				int size = q.size(); //현재 time에서 활성인 바이러스만 복제
				for(int s=0;s<size;s++) {
					int[] out = q.poll();
					for(int p=0;p<4;p++) {
						int y = out[0] + py[p];
						int	 x = out[1] + px[p];
						if(y<0 || x<0 || y>N-1 || x>N-1 || check[y][x] ) continue;
						if(map[y][x] !=1) { //벽이 아니라면
							q.offer(new int[] {y,x});
							check[y][x] = true;
							if(map[y][x] == 0) zero--; // 빈칸에 복제되었다면 zero를 감소시킨다.
						}
					}
				}
				time++; //시간 증가
			}
			return;
		}
		for(int i=start;i<=virus.size()-M+cnt;i++) {
			set[cnt] = i; //활성바이러스 조합
			bfs(i+1, cnt+1,N, M,set, map, virus,zero);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());
		int map[][] = new int[N][N],zero=0; //map = 연구소 모양 , zero=빈칸의 갯수
		List<int[]> virus = new ArrayList<int[]>(); //비활성 바이러스들의 위치를 저장
		for(int i=0;i<N;i++) {
			stz = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j]= Integer.parseInt(stz.nextToken());
				if(map[i][j]==0) zero++;
				else if(map[i][j] ==2) virus.add(new int[] {i,j});
			}
		}
		bfs(0,0,N,M, new int[M], map, virus, zero);
		System.out.println(MIN==Integer.MAX_VALUE?-1:MIN); //퍼질수 있는 방법이 없다면 -1
	}//main
}
