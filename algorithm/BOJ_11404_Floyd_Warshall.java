import java.util.*;
import java.io.*;
/**
 * [백준] 11404 - 플로이드
 * @author WOOHJ
 * - 플로이드 와샬 알고리즘
 *
 */
public class BOJ_11404_Floyd_Warshall {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		int cost[][] = new int[n+1][n+1];
		for(int i=1;i<=n;i++) {
			Arrays.fill(cost[i], 100000000);
			cost[i][i] = 0;
		}
		
		for(int i=0;i<m;i++) {
			StringTokenizer stz = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(stz.nextToken());
			int e = Integer.parseInt(stz.nextToken());
			int c = Integer.parseInt(stz.nextToken());
			cost[s][e] = Integer.min(cost[s][e], c); // 노선이 같은 버스가 여러개 존재할 수 있으므로 제일 작은 요금만 저장한다.
		}
		
		
		for(int k=1;k<=n;k++) // k = 경유 지점
			for(int i=1;i<=n ;i++) // i = 출발 지점
				for(int j=1;j<=n;j++) // j = 도착 지점
					cost[i][j] = Integer.min(cost[i][j], cost[i][k]+cost[k][j]); // i->j vs i->k->j 의 요금 비교
		
		for(int i=1;i<=n;i++) {
			StringBuilder sb = new StringBuilder();
			for(int j=1;j<=n;j++) sb.append(( cost[i][j] == 100000000 ? 0 : cost[i][j] )+ " ");
			System.out.println(sb.toString());
		}
	}
}