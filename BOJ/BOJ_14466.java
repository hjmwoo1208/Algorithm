import java.util.*;
import java.io.*;
public class BOJ_14466 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stz.nextToken());
		int K = Integer.parseInt(stz.nextToken());
		int R = Integer.parseInt(stz.nextToken());
		
		boolean farm[][][] = new boolean[4][N+1][N+1]; //방향,r,c
		int py[] = {-1,0,1,0}, px[] = {0,1,0,-1}; //상우하좌
		for(int i=0;i<R;i++) {
			stz = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(stz.nextToken());
			int c1 = Integer.parseInt(stz.nextToken());
			int r2 = Integer.parseInt(stz.nextToken());
			int c2 = Integer.parseInt(stz.nextToken());
			if(r1==r2) {
				if(c1>c2) {
					farm[3][r1][c1] = true;
					farm[1][r2][c2] = true;
				}else {
					farm[1][r1][c1] = true;
					farm[3][r2][c2] = true;
				}
			}else {
				if(r1>r2) {
					farm[0][r1][c1] = true;
					farm[2][r2][c2] = true;
				}else {
					farm[2][r1][c1] = true;
					farm[0][r2][c2] = true;
				}
			}
		}
		int cow[][] = new int[K][2];
		for(int i=0;i<K;i++) {
			stz = new StringTokenizer(br.readLine());
			cow[i] = new int[] {Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken())};
		}
		//----입력끝----
		
		int CNT = 0;
		for(int i=0;i<K-1;i++) 
			for(int j=i+1;j<K;j++) {
				Queue<int[]> q = new LinkedList<int[]>(); // r,c,flag(길 OX)
				boolean visited[][][] = new boolean[2][N+1][N+1]; //1 : 길 사용
				q.offer(new int[] {cow[i][0] ,cow[i][1],0});
				visited[0][cow[i][0]][cow[i][1]] = true;
				visited[1][cow[i][0]][cow[i][1]] = true;
				
				boolean load = false; //true : 길 건너기 없이 만날 수 있음
				
				while(!q.isEmpty()) {
					int out[] = q.poll();
					if(out[0] == cow[j][0] && out[1]==cow[j][1]) {
						if(out[2]==0) {
							load = true;
							break;
						}
						continue;
					}
					for(int p=0;p<4;p++) {
						int y = out[0] + py[p];
						int x = out[1] + px[p];
						if(y<1 ||x<1||y>N||x>N) continue;
						if(farm[p][out[0]][out[1]]) {
							if(visited[1][y][x]) continue;
							q.offer(new int[] {y,x,1});
							visited[1][y][x] = true;
						}else {
							if(visited[out[2]][y][x]) continue;
							q.offer(new int[] {y,x,out[2]});
							visited[out[2]][y][x] = true;
						}
					}
				}
				if(!load) CNT++;
			}
		System.out.println(CNT);
	}
}