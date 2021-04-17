import java.util.*;
import java.io.*;

public class BOJ_1400 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer stz = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(stz.nextToken());
			int N = Integer.parseInt(stz.nextToken());
			if(M==0 && N == 0) System.exit(0); //종료
			char map[][] = new char[M][N];
			int light = 0, time=0, py[] = {0,-1,0,1}, px[] = {-1,0,1,0}; //좌 상 우 하
			Queue<int[]> truck =  new LinkedList<int[]>();
			boolean visited[][] = new boolean[M][N], arrival = false;
			for(int i=0;i<M;i++) {
				map[i] = br.readLine().toCharArray();
				for(int j=0;j<N;j++) {
					if(map[i][j] >= '0' && map[i][j] <=9) light++;
					if(map[i][j]=='A') {
						truck.offer(new int[] {i,j,4}); // 4=전방향
						visited[i][j] = true;
					}
				}
			}
			int lightInfo[][] = new int[light][5]; //초기 2,동서 0,남북 1, 현재 방향 3, 현재 방향 cnt 4
			for(int i=0;i<light;i++) {
				stz = new StringTokenizer(br.readLine());
				i = Integer.parseInt(stz.nextToken());
				String str= stz.nextToken();
				if(str.equals("-")) lightInfo[i][2] = 1;
				else lightInfo[i][2] = 2;
				lightInfo[i][0] = Integer.parseInt(stz.nextToken());
				lightInfo[i][1] = Integer.parseInt(stz.nextToken());
				lightInfo[i][3] = lightInfo[i][2];
				lightInfo[i][4] = 1;
			}
			//--
			simul :
			while(!truck.isEmpty()) {
				int size = truck.size();
				for(int s=0;s<size;s++) {
					int out[]	= truck.poll();
					if(map[out[0]][out[1]]=='B') {
						arrival = true;
						break simul;
					}
					for(int p=0;p<4;p++) {
						int y = out[0] + py[p];
						int x = out[1] + px[p];
						if(y<0 || x<0 || y>=M || x>=N || visited[y][x] || map[y][x] == '.') continue; 
						if(map[y][x] == '#') {
							visited[y][x] = true;
							truck.offer(new int[] {y,x,p});
							continue;
						}
						//교차로 일 때
						int pp = out[2]%2;
						if(lightInfo[map[y][x]][3] == pp) {
							visited[y][x] = true;
							truck.offer(new int[] {y,x,4});
						}else truck.offer(new int[] {out[0],out[1],out[2]});
					}
				}
				time++;
				for(int i=0;i<light;i++) { //신호바꾸기
					if(lightInfo[i][4]==lightInfo[i][lightInfo[i][3]]) {
						lightInfo[i][3] =  (lightInfo[i][3]+1)%2;
						lightInfo[i][4] = 1;
					}else lightInfo[i][4]++;
				}
			}
			//--
			System.out.println(arrival==false?"impossible":time);
		}
	}
}