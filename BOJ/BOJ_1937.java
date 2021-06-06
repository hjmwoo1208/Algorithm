import java.io.*;
import java.util.*;
/* 백준  - 욕심쟁이 판다
 * - 일방적인 방식으로 BFS하고 저장하다가 경로를 중복 탐색하는 경우가 있음
 * - DFS를 사용해서 경로를 탐색해야한다.
 * - 이미 DP 배열에 메모이제이션 되어 있는 경우는 탐색을 패스하고 +1값으로 비교한다.
 * */
public class BOJ_1937 {
	static int map[][], DP[][], N;
	static int dfs(int i, int j, int level) {
		int py[] = {-1,0,1,0}, px[] = {0,1,0,-1}, count = 1;
		for(int p =0;p<4;p++) {
			int y = i + py[p];
			int x = j+ px[p];
			if(y<0 || x<0 || y>=N || x>=N || map[y][x] <= map[i][j]) continue;
			if(DP[y][x] >0 && DP[y][x] + 1 > count ) count = DP[y][x] + 1;
			if(DP[y][x] == 0) {
				int max = dfs(y,x,level+1) + 1;
				if(max > count) count = max;
			}
		}
		DP[i][j] = count;
		return DP[i][j];
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		DP = new int[N][N];
	
		for(int i=0;i<N;i++) {
			StringTokenizer stz = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) 	map[i][j] = Integer.parseInt(stz.nextToken());
		}
		int MAX = 0;
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++) {
				int max = dfs(i,j,1);
				if(max>MAX) MAX = max;
			}
		System.out.println(MAX);
	}
}