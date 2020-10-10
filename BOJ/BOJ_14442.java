import java.util.*;
import java.io.*;
public class BOJ_14442 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz=  new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());
		int K = Integer.parseInt(stz.nextToken());
		String map[] = new String[N];
		int min = -1;
		for(int i=0;i<N;i++) map[i] = br.readLine();
		boolean check[][][] = new boolean[N][M][K+1];
		for(int i=0;i<K+1;i++) check[0][0][i] = true;
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {0,0,0,0}); //y,x,부신 벽의 갯수,총 이동 거리
		int py[] = {-1,0,1,0};
		int px[] = {0,1,0,-1};
		while(!(q.isEmpty())) {
			int[] out = q.poll();
			if(out[0] == N-1 && out[1] ==M-1) {
				min = out[3]+1;
				break;
			}
			for(int p=0;p<4;p++) {
				int y = out[0] + py[p];
				int x = out[1] + px[p];
				if(y<0 || x<0|| y>N-1 || x>M-1 ) continue;
				if(map[y].charAt(x) == '1' && out[2]<K && check[y][x][out[2]+1]==false) {
					check[y][x][out[2]+1]= true;
					q.offer(new int[] {y,x,out[2]+1,out[3]+1});
				}
				if(map[y].charAt(x) == '0' && check[y][x][out[2]]==false) {
					check[y][x][out[2]] = true;
					q.offer(new int[] {y,x,out[2],out[3]+1});
				}
			}
		}
		System.out.println(min);
	}
}