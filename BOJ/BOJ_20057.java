import java.util.*;
import java.io.*;
public class BOJ_20057 {
/* 마법사 상어와 토네이도 - 성공 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int A[][] = new int[N][N], total = 0;
		for(int i=0;i<N;i++) {
			StringTokenizer stz = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				A[i][j] = Integer.parseInt(stz.nextToken());
				total += A[i][j];
			}
		}
		int r=N/2,c=N/2,sand=0, p=0, pcnt=0; // p 현재 이동 방향, pcnt 횟수
		int pLength[] = {1,1,2,2}, rate[] = {1,7,2,10,5,10,7,2,1};
		int py[] = {0,1,0,-1}, px[] = {-1,0,1,0};
		int sandy[][] = {{-1,-1,-2,-1,0,1,1,2,1},
  						  {0,1,1,2,3,2,1,1,0},
						  {1,1,2,1,0,-1,-1,-2,-1},
						  {0,-1,-1,-2,-3,-2,-1,-1,0}};
		int sandx[][] = {{0,-1,-1,-2,-3,-2,-1,-1,0},
						  {-1,-1,-2,-1,0,1,1,2,1},
						  {0,1,1,2,3,2,1,1,0},
						  {1,1,2,1,0,-1,-1,-2,-1}};
		while(true) {
			int yy = r + py[p];
			int yx = c + px[p];
			int ay= yy+py[p];
			int ax = yx+px[p];
			
			int ysand = A[yy][yx];
			for(int i=0;i<9;i++) {
				int y = r + sandy[p][i];
				int x = c + sandx[p][i];
				int tmpSand = (A[yy][yx]*rate[i])/100;
				ysand -= tmpSand;
				if(y<0 || x<0 || y>=N || x>=N) sand += tmpSand;
				else A[y][x] += tmpSand;
			}
			
			if(ay<0 || ax<0 || ay>=N || ax>=N) sand += ysand;
			else A[ay][ax] += ysand;
			
			A[yy][yx] = 0;
			r = yy;
			c = yx;
			
			pcnt++;
			if(pcnt == pLength[p]) {
				pLength[p] += 2;
				p = (p+1)%4;
				pcnt=0;
			}
			if(r==0 && c==0) break;
		}
		System.out.println(sand);
	}
}