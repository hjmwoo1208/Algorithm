import java.util.*;
import java.io.*;
/* Maaaaaaaaaze -  성공
 * 
 * <풀이 과정>
 * 1. 각 판을 반시계 방향으로 돌린 모든 경우를 미리 구한다. (각 판 별로 4가지)
 * 2. 각 판 별로 반시계 방향으로 몇번 돌린 모양을 선택한지 경우의 수를 구한다. (per 메소드 -> set[])
 * 3. 각 판이 몇번째 순서로 배치될지에 대한 순서를 구한다. (per2메소드 -> finSet[])
 * 4. 2와 3을 통해서 판의 순서로 미로를 셋팅한 후 [0][0][0]에서 [4][4][4]까지 BFS를 한다.
 * */

public class BOJ_16985 {
	static int MIN = -1, set[] = new int[5], finSet[] = new int[5];
	
	static int[][][][] Rotation(int initMaze[][][]) {
		int maze[][][][] = new int[4][5][5][5];
		for(int i=0;i<5;i++) maze[0][i] = initMaze[i];
		
		int py1[] = {0,0,0,0,0,1,2,3,4,4,4,4,4,3,2,1};
		int px1[] = {0,1,2,3,4,4,4,4,4,3,2,1,0,0,0,0};
		
		int py2[] = {1,1,1,2,3,3,3,2};
		int px2[] = {1,2,3,3,3,2,1,1};
		
		for(int n=0;n<5;n++){
			int temp1[] = new int[16];
			for(int i=0;i<16;i++) temp1[i] = initMaze[n][py1[i]][px1[i]];
			int temp2[] = new int[8];
			for(int i=0;i<8;i++) temp2[i] = initMaze[n][py2[i]][px2[i]];
			
			for(int r=1;r<4;r++) {
				for(int i=0;i<16;i++) {
					int add = 4*r;
					maze[r][n][py1[i]][px1[i]] = temp1[(i+add)%16	];
				}
				for(int i=0;i<8;i++) {
					int add = 2*r;
					maze[r][n][py2[i]][px2[i]] = temp2[(i+add)%8];
				}
				maze[r][n][2][2] = initMaze[n][2][2]; //틀린 이유 : 각 판의 [2][2] 가운데 칸을 빼먹음..ㅠㅠ
			}
		}
		return maze;
	}
	
	static void per(int idx, int[][][][] maze) { // 0~4까지 회전한 판을 어떤걸 가져갈지 셋팅
		if(idx == 5) {
			per2(0,maze);
			return;
		}
		for(int i=0;i<4;i++) {
			set[idx] = i;
			per(idx+1,maze);
		}
	}
	static boolean check[] = new boolean[5];
	static void per2(int num, int[][][][] maze) { // 0~5가지의 판을 어떤 순서로 배치할지를 셋팅
		if(num==5) {
			int thisMaze[][][] = new int[5][5][5];
			for(int i=0;i<5;i++) thisMaze[i] = maze[set[finSet[i]]][finSet[i]];
			if(thisMaze[0][0][0] == 0) return; // 시작점이 참가자가 들어 갈 수 없는 칸이면 return 한다. 
			
			int py[] = {-1,0,1,0,0,0}; // (현재칸 기준) 상우하좌 앞 뒤
			int px[] = {0,1,0,-1,0,0};
			int pz[] = {0,0,0,0,-1,1};
			
			Queue<int[]> q = new LinkedList<>();
			boolean visited[][][] = new boolean[5][5][5];
			visited[0][0][0] = true;
			q.offer(new int[] {0,0,0,0}); //z,y,x,cnt
			while(!(q.isEmpty())) {
				int out[] = q.poll();
				if(out[0] == 4 && out[1]==4 && out[2] == 4) {
					if(MIN==-1) MIN = out[3];
					else {
						if(MIN > out[3]) MIN = out[3];
					}
					return;
				}
				for(int p=0;p<6;p++) {
					int z = out[0] + pz[p];
					int y = out[1] + py[p];
					int x = out[2] + px[p];
					if(z<0 || y<0 || x<0 || z>4 || y>4 || x>4 || visited[z][y][x] || thisMaze[z][y][x] == 0) continue;
					q.offer(new int[] {z,y,x,out[3]+1});
					visited[z][y][x] = true;
				}
			}
			return;
		}
		for(int i=0;i<5;i++)
			if(!check[i]) {
				check[i] = true;
				finSet[i] = num;
				per2(num+1,maze);
				check[i] = false;
			}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int initMaze[][][] = new int[5][5][5];
		for(int n=0;n<5;n++)
			for(int i=0;i<5;i++) {
				StringTokenizer stz = new StringTokenizer(br.readLine());
				for(int j=0;j<5;j++) initMaze[n][i][j]  = Integer.parseInt(stz.nextToken());
			}
		int maze[][][][] = Rotation(initMaze); //방향 바뀐거 , 판 번호
		per(0,maze);
		System.out.println(MIN);
	}
}