import java.util.*;
import java.io.*;
/* < 백준 - 게임 >
 * - DP
 * - 메모이제이션으로 해당 위치에서의 최대 이동 가능 수를 저장하여 중복 탐색을 제거
 * - DFS를 사용해서 무제한 이동이 가능한지 판단
 */
public class BOJ_1103 {
	static int N, M, MAX = 0, memo[][];
	static int dfs(char map[][], boolean visited[][], int count, int i, int j) {
		if(MAX == -1 ) return -1;
		int py[] = {-1,0,1,0}, px[] = {0,1,0,-1};
		int len = map[i][j]-'0', max=1; 
		for(int p=0;p<4;p++) {
			if(max == -1) continue;
			int y = i+py[p]*len;
			int x = j+px[p]*len;
			if(y<0 || x<0 || y>=N || x>=M || map[y][x] == 'H') max = Math.max(1, max);
			else {
				if(visited[y][x]) max = -1; // 현재 경로에서 다시 방문하면 무제한 이동가능
				else {
					if(memo[y][x]!=0) { //이미 탐색이 된 곳
						if(memo[y][x] == -1) max = -1;
						else max = Math.max(memo[y][x]+1, max);
					}else {
						visited[y][x] = true;
						int cnt = dfs(map,visited,count+1,y,x);
						if(cnt == -1) max = -1;
						if(max > -1) max = Math.max(max, cnt+1);
						visited[y][x] = false;
					}
				}
			}
		}
		if(max == -1) MAX = -1;
		return memo[i][j] = max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stz.nextToken());
		M = Integer.parseInt(stz.nextToken());
		memo = new int [N][M];
		char map[][] = new char[N	][M];
		for(int i=0;i<N;i++) map[i] = br.readLine().toCharArray();
		boolean check[][] = new boolean [N][M];
		check[0][0] = true;
		dfs(map,check,1,0,0);
		if(MAX == -1) System.out.println(-1);
		else {
			int res = 0;
			for(int i=0;i<N;i++)
				for(int j=0;j<M;j++)
					res = Math.max(res,memo[i][j]);
			System.out.println(res);
		}
	}
}