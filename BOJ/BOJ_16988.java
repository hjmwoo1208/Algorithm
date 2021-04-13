import java.util.*;
import java.io.*;
/* Baaaaaaaaaduk2 (Easy) - 성공
 * <풀이방법>
 * 1. 바둑판에 2개의 돌을 놓는 모든 조합을 구한다.
 * 2. 각각의 조합의 바둑판에서 상대방 돌을 기준으로 BFS를 한다. 
 * 		- BFS 도중 빈칸(0)을 만나면 잡는데 실패한 그룹
 * 		- BFS 도중 빈칸을 만나도 그룹화하기 위해서 계속 BFS를 진행한다.
 * 3. 빈칸을 만나지 않았다면, 잡힌 그룹이므로 잡은 그룹의 합에 더해준다.
 * 4. 최대 잡은 갯수와 비교하여 갱신한다.
 * */
public class BOJ_16988 {
	static int MAX = 0;
	static void BFS(int[][] map, int N, int M, int index, int start[]) {
		if(index==2) {
			boolean check[][] = new boolean[N][M];
			int py[] = {-1,0,1,0}, px[] = {0,1,0,-1};
			int sum = 0; //현재 모양에서 잡은 바둑의 갯수
			for(int i=0;i<N;i++) 
				for(int j=0;j<M;j++) 
					if(map[i][j] == 2 && !check[i][j]) {
						Queue<int[]> q = new LinkedList<>();
						q.offer(new int[] {i,j});
						check[i][j] = true;
						int count = 1; //그룹 내의 바둑의 수를 체크
						boolean fail = false;
						while(!(q.isEmpty())) {
							int out[] = q.poll();
							for(int p=0;p<4;p++) {
								int y = out[0] + py[p];
								int x = out[1] + px[p];
								if(y<0 || x<0 || y>=N || x>=M  || map[y][x] == 1||check[y][x]) continue;
								if(map[y][x] == 0) fail = true; //빈칸을 만났을 때 -> 실패그룹
								q.offer(new int[] {y,x});
								check[y][x] = true;
								count++;
							}
						}
						if(!fail) sum += count;
					}
			if(sum > MAX) MAX = sum;
			return;
		}
		int x = start[1];
		for(int i=start[0];i<N;i++,x=0) 
			for(int j=x;j<M;j++)
				if(map[i][j] == 0) {
					map[i][j] = 1;
					int next[] = new int[2];
					next[0] = j+1>=M? i+1:i;
					next[1] = j+1>=M? 0:j;
					BFS(map,N,M,index+1,next);
					map[i][j] = 0;
				}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int N =Integer.parseInt(stz.nextToken());
		int M =Integer.parseInt(stz.nextToken());
		int map[][] = new int[N][M];
		for(int i=0;i<N;i++) {
			stz = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) map[i][j] =Integer.parseInt(stz.nextToken());
		}		
		BFS(map,N,M,0, new int[] {0,0});
		System.out.println(MAX);
	}
}