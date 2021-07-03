import java.util.*;
import java.io.*;
/* 백준 - 직사각형 탈출
 * - BFS
 * - 직사각형의 왼쪽 모서리만 방문 체크
 * - 탈출 실패시 -1 출력 주의
 * */
public class BOJ_16959 {
	public static void main(String[] args) throws IOException {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());
		int map[][] = new int[N+1][M+1];
		for(int i=1;i<N+1;i++) {
			stz = new StringTokenizer(br.readLine());
			for(int j=1;j<M+1;j++) map[i][j] = Integer.parseInt(stz.nextToken());
		}
		stz = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(stz.nextToken());
		int W = Integer.parseInt(stz.nextToken());
		int S[] = new int[] {Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken())};
		int F[] = new int[] {Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken())};
		
		Queue<int[]> q  = new LinkedList<int[]> ();
		boolean check[][] = new boolean[N+1][M+1];
		check[1][1] = true;
		q.offer(new int[] {S[0],S[1],0});

		int py[] = {-1,0,1,0}, px[] = {0,1,0,-1},time =-1;
		while(!(q.isEmpty())) {
			int out[] = q.poll();
			if(out[0]==F[0] && out[1] == F[1]) {
				time = out[2];
				break;
			}
			for(int p=0;p<4;p++) {
				int y = out[0] + py[p];
				int x = out[1] + px[p];
				if(y<1 || x< 1 ||y>N || x> M || check[y][x]) continue;
				if(y+H <1 || x+W <1 ||y+H-1>N ||x+W-1>M) continue;
				boolean flag = false;
				switch (p) {
				case 0:
					for(int n=x;n<x+W;n++)
						if(map[y][n] == 1) {
							flag = true;
							break;
						}
					break;
				case 1:
					for(int n=y;n<y+H;n++	)
						if(map[n][x+W-1] == 1) {
							flag = true;
							break;
						}
					break;				
				case 2:
					for(int n=x;n<x+W;n++)
						if(map[y+H-1][n] == 1) {
							flag = true;
							break;
						}
					break;
				case 3:
					for(int n=y;n<y+H;n++	)
						if(map[n][x] == 1) {
							flag = true;
							break;
						}
					break;
				}
				if(!flag) {
					check[y][x] = true;
					q.offer(new int[] {y,x,out[2]+1});
				}
			}
		}
		System.out.println(time);
	}
}