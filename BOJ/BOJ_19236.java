import java.util.*;
import java.io.*;
public class BOJ_19236 {
	static int MAX = 0;
	static int py[] = {-1,-1,0,1,1,1,0,-1};
	static int px[]	 = {0,-1,-1,-1,0,1,1,1};
	static void moveFish(int[] shark, List<Integer> alive, int map[][][], int max) { //물고기들이 이동하는 
		for(int f=0;f<alive.size();f++) {
			find :
			for(int i=0;i<4;i++) //살아있는 물고기 위치 찾기
				for(int j=0;j<4;j++) 
					if(map[0][i][j	] == alive.get(f)) {
						for(int p=0;p<8;p++) {
							int y = i+py[map[1][i][j]];
							int x = j+px[map[1][i][j]];
							if(y<0||x<0||y>3||x>3|| map[0][y][x] ==20) {
								map[1][i][j] = (map[1][i][j]+1)%8; //방향 바꾸기
								continue;
							}
							//swap
							int swapNum = map[0][y][x];
							int swapP = map[1][y][x];
							map[0][y][x] = map[0][i][j];							
							map[1][y][x] = map[1][i][j];
							map[0][i][j] = swapNum;
							map[1][i][j] = swapP; 
							break find;
						}
					}
		}
		moveShark(shark,alive,map,max);
	}
	static void moveShark(int[] shark, List<Integer> alive, int map[][][], int max) {
		for(int mul=1;mul<4;mul++) {
			int y = shark[0] + py[map[1][shark[0]][shark[1]]]*mul; //상어 - 이동 가능한 구역 찾기			
			int x = shark[1] + px[map[1][shark[0]][shark[1]]]*mul;
			if(y<0|| x<0 || y>3 || x>3) break; //범위 넘어가면 더 볼 필요X
			if(map[0][y][x]==0) continue; //0이면 pass
			//먹을 물고기가 있다면
			int newMap[][][] = new int[2][4][4];
			for(int i=0;i<2;i++)
				for(int j=0;j<4;j++) System.arraycopy(map[i][j], 0, newMap[i][j], 0, 4);
			newMap[0][y][x] = 20;
			newMap[0][shark[0]][shark[1]] = 0;
			List<Integer> tmpAlive = new ArrayList<Integer>();
			for(int i=0;i<4;i++)
				for(int j=0;j<4;j++)
					if(newMap[0][i][j]>0 && newMap[0][i][j]<20) tmpAlive.add(newMap[0][i][j]);
			Collections.sort(tmpAlive); //살아있는 물고기 번호 순으로
			moveFish(new int[] {y,x}, tmpAlive, newMap, max+map[0][y][x]);
		}
		if(max > MAX) MAX = max;
		return;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int map[][][] = new int [2][4][4]; //0-번호, 1-방향
		for(int i=0;i<4;i++) {
			StringTokenizer stz = new StringTokenizer(br.readLine());
			for(int j=0;j<4;j++) {
				map[0][i][j] = Integer.parseInt(stz.nextToken());
				map[1][i][j] = Integer.parseInt(stz.nextToken())-1;
			}
		}
		MAX = map[0][0][0];		
		map[0][0][0] = 20; //상어는 0,0에서 시작
		List<Integer> alive = new ArrayList<Integer>(); //살아있는 상어들의 번호
		for(int i=1;i<17;i++)
			if(MAX!=i) alive.add(i);
		moveFish(new int[] {0,0}, alive, map, MAX);
		System.out.println(MAX);
	}//main
}