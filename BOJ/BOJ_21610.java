import java.util.*;
import java.io.*;
/* 마법사 상어와 비바라기 - 21610번
   - 시뮬레이션
 */
public class BOJ_21610 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());
		int A[][] = new int[N][N];
		for(int i=0;i<N;i++) {
			stz = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) A[i][j] = Integer.parseInt(stz.nextToken());
		}
		int magic[][] = new int[M][2];
		for(int i=0;i<M;i++) {
			stz = new StringTokenizer(br.readLine());
			magic[i] = new int[] {Integer.parseInt(stz.nextToken())-1, Integer.parseInt(stz.nextToken())};
		}	
		int py[] = {0,-1,-1,-1,0,1,1,1}, px[] = {-1,-1,0,1,1,1,0,-1};
		List<int[]> cloud = new ArrayList<int[]>();
		cloud.add(new int[] {N-1,0});
		cloud.add(new int[] {N-1,1});
		cloud.add(new int[] {N-2,0});
		cloud.add(new int[] {N-2,1});
		
		for(int m=0;m<M;m++) {
			int size = cloud.size();
			boolean remove[][] = new boolean[N][N];
			for(int s=0;s<size;s++) {
				int y = (cloud.get(s)[0]+ magic[m][1]*(N+py[magic[m][0]]))%N;
				int x = (cloud.get(s)[1]+ magic[m][1]*(N+px[magic[m][0]]))%N;
				cloud.get(s)[0] = y;
				cloud.get(s)[1] = x;
				remove[y][x] = true;
				A[y][x]++;
			}
			for(int s=0;s<size;s++) {
				int r = cloud.get(s)[0];
				int c = cloud.get(s)[1];
				int cnt = 0;
				for(int p=1;p<8;p+=2) {
					int y = r+py[p];
					int x = c+px[p];
					if(y<0 || x<0 ||y>=N || x>=N || A[y][x]<1 ) continue;
					cnt++;
				}
				A[r][c] += cnt;
			}
			cloud.clear();
			for(int i=0;i<N;i++)
				for(int j=0;j<N;j++) {
					if(remove[i][j]) continue;
					if(A[i][j]>1) {
						cloud.add(new int[] {i,j});
						A[i][j] -= 2;
					}
				}
		}
		int sum = 0;
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++) sum +=A[i][j];
		System.out.println(sum);
		
	}//main
}