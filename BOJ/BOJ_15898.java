import java.util.*;
import java.io.*;
/* 피아의 아틀리에 ~신비한 대회의 연금술사~ - 성공 
 * <풀이>
 * 1. 미리 효능과 색상을 4번 돌린다. stat(color)[][][][] = new int[재료 인덱스][재료 돌린 횟수][행][열]
 * 2. DFS로 행 0~1, 열 0~1의 시작으로 조합을 한다.
 * */
public class BOJ_15898 {
	static int MAX = 0;
	static void DFS(int cnt, int N,int stat[][][][], char color[][][][], boolean check[], int setStat[][], char setColor[][]) {
		if(cnt==3) {
			int R=0,G=0,B=0,Y=0;
			for(int i=0;i<5;i++)
				for(int j=0;j<5;j++) {
					if(setColor[i][j]=='R') R += setStat[i][j];
					else if(setColor[i][j] == 'G') G += setStat[i][j];
					else if(setColor[i][j] == 'B') B += setStat[i][j];
					else if(setColor[i][j]=='Y') Y+= setStat[i][j];
				}
			int sum = 7*R + 5*B + 3*G + 2*Y;
			MAX = MAX<sum? sum:MAX;
			return;
		}
		for(int n=0;n<N;n++)
			if(!check[n]) {
				check[n] =true;
				for(int i=0;i<2;i++)
					for(int j=0;j<2;j++) {
						for(int p=0;p<4;p++) {
							 int tmpStat[][] = new int[5][5];
							 for(int l=0;l<5;l++) System.arraycopy(setStat[l], 0, tmpStat[l], 0, 5);
							 char tmpColor[][] = new char[5][5];
							 for(int l=0;l<5;l++) System.arraycopy(setColor[l], 0, tmpColor[l], 0, 5);
							 for(int y=0;y<4;y++)
								 for(int x=0;x<4;x++) {
									 int ny = i+y;
									 int nx = j+x;
									 tmpStat[ny][nx] += stat[n][p][y][x];
									 tmpStat[ny][nx] = tmpStat[ny][nx] < 0 ? 0 : tmpStat[ny][nx];
									 tmpStat[ny][nx] = tmpStat[ny][nx]>9 ? 9 : tmpStat[ny][nx];
									 if(color[n][p][y][x] == 'W') continue;
									 tmpColor[ny][nx] = color[n][p][y][x];
								 }
							 DFS(cnt+1,N,stat,color,check,tmpStat,tmpColor);
						}
					}
				check[n] = false;
			}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int stat[][][][] = new int[N][4][4][4];
		char color[][][][] = new char[N][4][4][4];
		for(int n=0;n<N;n++) {
			for(int i=0;i<4;i++) {
				StringTokenizer stz = new StringTokenizer(br.readLine());
				for(int j=0;j<4;j++) stat[n][0][i][j] = Integer.parseInt(stz.nextToken());
			}
			for(int i=0;i<4;i++) {
				StringTokenizer stz = new StringTokenizer(br.readLine());
				for(int j=0;j<4;j++) color[n][0][i][j] = stz.nextToken().charAt(0);
			}
		}
		int py1[] = {0,0,0,0,1,2,3,3,3,3,2,1}, px1[] = {0,1,2,3,3,3,3,2,1,0,0,0};
		int py2[] = {1,1,2,2}, px2[] = {1,2,2,1};
		for(int n=0;n<N;n++) 
			for(int p=1;p<4;p++) {
				for(int idx=0;idx<12;idx++) {
					stat[n][p][py1[idx]][px1[idx]] = stat[n][p-1][py1[(idx+3)%12]][px1[(idx+3)%12]];
					color[n][p][py1[idx]][px1[idx]] = color[n][p-1][py1[(idx+3)%12]][px1[(idx+3)%12]];
				}
				for(int idx=0;idx<4;idx++) {
					stat[n][p][py2[idx]][px2[idx]] = stat[n][p-1][py2[(idx+1)%4]][px2[(idx+1)%4]];
					color[n][p][py2[idx]][px2[idx]] = color[n][p-1][py2[(idx+1)%4]][px2[(idx+1)%4]];
				}
			}
		char setColor[][] = new char[5][5];
		for(int i=0;i<5;i++) Arrays.fill(setColor[i], 'W');
		DFS(0,N,stat,color,new boolean[N],new int[5][5], setColor);
		System.out.println(MAX);
	}
}