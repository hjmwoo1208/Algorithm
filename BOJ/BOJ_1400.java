import java.util.*;
import java.io.*;
/*  화물차 - 성공
 * ShortCode 1st - 제출번호 : 28461267
 * <주의>
 * - 런타임 에러 주의 : 종료 입력, 공백 입력 주의
 * - 틀렸습니다 주의 : 방문 체크는 3차원 배열로
 * 
 * */
public class BOJ_1400 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer stz = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(stz.nextToken());
			int N = Integer.parseInt(stz.nextToken());
			if(M==0 && N == 0) System.exit(0); //종료
			char map[][] = new char[M][N];
			//light : 교차로 갯수
			int light = 0, time=0, py[] = {0,-1,0,1}, px[] = {-1,0,1,0}; //(서 남 동 북)  0,2 : 동서 ->lightInfo index 0 / 1,3 : 남북 light index 1
			Queue<int[]> truck =  new LinkedList<int[]>();
			boolean visited[][][] = new boolean[4][M][N], arrival = false; //방문 체크는 방향별로 따로 해주어야한다.
			for(int i=0;i<M;i++) {
				map[i] = br.readLine().toCharArray();
				for(int j=0;j<N;j++) {
					if(map[i][j] >= '0' && map[i][j] <='9') light++;
					if(map[i][j]=='A') {
						truck.offer(new int[] {i,j,4}); // 4=전방향
						for(int p=0;p<4;p++) visited[p][i][j] = true;
					}
				}
			}
			int lightInfo[][] = new int[1][1]; //초기 2,동서 0,남북 1, 현재 방향 3, 현재 방향 cnt 4
			if(light>0) {
				lightInfo = new int[light][5];
				for(int i=0;i<light;i++) {
					stz = new StringTokenizer(br.readLine());
					i = Integer.parseInt(stz.nextToken());
					String str= stz.nextToken();
					if(str.equals("-")) lightInfo[i][2] = 0;
					else lightInfo[i][2] = 1;
					lightInfo[i][0] = Integer.parseInt(stz.nextToken());
					lightInfo[i][1] = Integer.parseInt(stz.nextToken());
					lightInfo[i][3] = lightInfo[i][2];
					lightInfo[i][4] = 1;
				}
			}
			simul :
			while(!truck.isEmpty()) {
				int size = truck.size();
				for(int s=0;s<size;s++) {
					int out[]	= truck.poll();
					if(map[out[0]][out[1]]=='B') { //도착지에 도착한 경우
						arrival = true;
						break simul;
					}
					for(int p=0;p<4;p++) {
						int y = out[0] + py[p];
						int x = out[1] + px[p];
						if(y<0 || x<0 || y>=M || x>=N || visited[p][y][x] || map[y][x] == '.') continue; 
						if(map[y][x]>='0' && map[y][x]<='9') { 	//교차로 일 때
							int pp = out[2]%2;
							if(lightInfo[map[y][x]-'0'][3] == pp) { // pp = 0 : 동서/ 1: 남북 / 교차로의 방향과 현재 방향이 일치할 때
								visited[p][y][x] = true;
								truck.offer(new int[] {y,x,4});
							}else truck.offer(new int[] {out[0],out[1],out[2]}); //다음이 교차로이지만 못 넘어갈경우 현재 위치에서 대기한다.
						}else { // #이거나 B이거나
							visited[p][y][x] = true;
							truck.offer(new int[] {y,x,p});
						}
					}
				}
				time++;
				for(int i=0;i<light;i++) { //신호바꾸기
					if(lightInfo[i][4]==lightInfo[i][lightInfo[i][3]]) { //신호가 바뀌어야하는 경우
						lightInfo[i][3] =  (lightInfo[i][3]+1)%2; // 다음 신호로 바꾸기 0 -> 1 / 1->0
						lightInfo[i][4] = 1; //1부터 다시 시작
					}else lightInfo[i][4]++; //그대로 신호를 유지하는 경우
				}
			}
			System.out.println(arrival==false?"impossible":time);
			br.readLine();
		}
	}
}