import java.util.*;
import java.io.*;
public class BOJ_4179 {
/* 불! - 성공 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int R= Integer.parseInt(stz.nextToken());
		int C = Integer.parseInt(stz.nextToken());
		char map[][] = new char[R][C];
		Queue<int[]> jihoon = new LinkedList<int[]>();
		Queue<int[]> fire = new LinkedList<int[]>();
		boolean visited[][] = new boolean[R][C], exit = false;
		for(int i=0;i<R;i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0;j<C;j++) {
				if(map[i][j]=='J') {
					visited[i][j] = true;
					map[i][j] = '.';
					jihoon.offer(new int[] {i,j,0});
				}
				if(map[i][j] =='F') fire.add(new int[] {i,j});
			}
		}
		
		int time=0, py[] = {-1,0,1,0}, px[] = {0,1,0,-1};
		simul :
		while(!jihoon.isEmpty()) {
			// 현재 분(위치)에서 1분 뒤 지훈이 탈출가능한 루트 
			int jhsize = jihoon.size();
			for(int s=0;s<jhsize;s++) {
				int out[] = jihoon.poll();
				if(map[out[0]][out[1]] == 'F') continue; // -1분 전에 불이 번진 곳
				if(out[0] == 0 || out[1] == 0 || out[0] ==R-1 || out[1] == C-1) { //경계에 도착함
					exit = true; //탈출 가능
					time = out[2]+1; // 1분 뒤 탈출 
					break simul; //현재가 최소 시간이므로 시뮬레이션을 종료한다.
				}
				for(int p=0;p<4;p++) {
					int y = out[0]  + py[p];
					int x = out[1] + px[p];
					if(y<0 || x<0 || y>=R || x>=C || visited[y][x] || map[y][x] == 'F' || map[y][x] == '#') continue;
					jihoon.offer(new int[] {y,x,out[2]+1});
					visited[y][x] = true;
				}
			}
			if(jihoon.isEmpty()) break; //더이상 이동할 공간 없으므로 시뮬레이션 종로
			
			//불 확산
			int fireSize = fire.size();
			for(int s=0;s<fireSize;s++) {
				int out[] = fire.poll();
				for(int p=0;p<4;p++) {
					int y = out[0] + py[p];
					int x = out[1] + px[p];
					if(y<0 || x<0 || y>=R || x>=C || map[y][x] == 'F' || map[y][x] == '#') continue;
					map[y][x] = 'F';
					fire.offer(new int[] {y,x});
				}
			}
		}
		System.out.println(exit==false?"IMPOSSIBLE":time);  //exit가 false면 탈출 불가능
	}
}