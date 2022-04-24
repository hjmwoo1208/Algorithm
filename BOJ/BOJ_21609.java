import java.util.*;
import java.io.*;
/**
 * [백준] 21609번 - 상어 중학교
 * @author WOOHJ
 * 탐색, 시뮬레이션, 우선순위 큐
 */
public class BOJ_21609 {
	static class Info implements Comparable<Info>{
		int r,c, size, rainbow, index;

		public Info(int r, int c, int size, int rainbow, int index) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
			this.rainbow = rainbow;
			this.index = index;
		}
		@Override
		public int compareTo(Info i) {
			if(this.size > i.size) return -1;
			else if(this.size == i.size ) {
				if(this.rainbow > i.rainbow ) return -1;
				else if(this.rainbow == i.rainbow) {
					if(this.r > i.r) return -1;
					else if(this.r == i.r) {
						if(this.c > i.c) return -1;
					}
				}
			}
			return 1;
		}
	}
	static int SCORE = 0;
	static int map[][] = new int[20][20];
	static boolean playGame(int N) {
		if(N == 1) return false;
		
		int py[] = {-1,0,1,0};
		int px[] = {0,1,0,-1};
		
		List<List<int[]>> groups = new ArrayList<>();
		List<Info> info = new ArrayList<Info>();
		// 그룹 찾기
		boolean visited[][] = new boolean[N][N];
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++) {
				if(visited[i][j] || map[i][j] == 0 || map[i][j] < 0 ) continue;
				Info groupInfo = new Info(i,j,1,0,groups.size());
				List<int[]> group = new ArrayList<int[]>();
				List<int[]> rainbow = new ArrayList<int[]>();
				Queue<int[]> q = new LinkedList<int[]>();
				group.add(new int[] {i,j});
				q.offer(new int[] {i,j});
				visited[i][j] = true;
				while(!q.isEmpty()) {
					int out[] = q.poll();
					for(int p=0;p<4;p++) {
						int y = out[0] + py[p];
						int x = out[1] + px[p];
						if(y<0 || x<0 || y>=N || x>=N || visited[y][x] || map[y][x] < 0 || (map[y][x] != 0 && map[y][x] != map[i][j])) continue;
						if(map[y][x] != 0 && map[y][x] != map[i][j]) continue;
						if(map[y][x] == 0 ) {
							groupInfo.rainbow++;
							rainbow.add(new int[] {y,x});
						}else {
							// 기준 블록 정보 갱신
							if(groupInfo.r > y) {
								groupInfo.r = y;
								groupInfo.c = x;
							}else if(groupInfo.r == y && groupInfo.c > x)
								groupInfo.c = x;
						}
						visited[y][x] = true;
						group.add(new int[] {y,x});
						q.offer(new int[] {y,x});
					}
				}
				
				groupInfo.rainbow = rainbow.size();
				// 무지개블록 방문 초기화
				for(int rc[] : rainbow) visited[rc[0]][rc[1]] = false;
				if(group.size() >= 2 ) {
					groupInfo.size = group.size();
					info.add(groupInfo);
					groups.add(group);
				}
			}
		if(groups.size() == 0) return false;
		Collections.sort(info);
		Info pick = info.get(0);
		SCORE += Math.pow(pick.size, 2);
		
		//그룹 삭제
		for(int rc[] : groups.get(pick.index)) map[rc[0]][rc[1]] = -2;
		
		// 중력 작용
		gravity(N);
		// 90도 반시계 회전
		turnMap(N);
		// 중력 작용
		gravity(N);
		
		return true;
	}
	
	static void gravity(int N) {
		for(int j=0;j<N;j++) 
			for(int i=N-1;i>-1;i--) {
				if(map[i][j] == -2) continue;
				if(map[i][j] == -1) continue;
				if(map[i][j] >= 0) {
					int num = map[i][j];
					map[i][j] = -2;
					for(int y=i;y<N;y++) {
						if( y+1 == N || map[y+1][j] == -1 || map[y+1][j] == 0 ||map[y+1][j] > 0) {
							map[y][j] = num;
							break;
						}else map[y][j] = -2;
					}
				}
			}
		
	}
	static void turnMap(int N) {
		int newMap[][] = new int[N][N];
		for(int i=0;i<N;i++) {
			List<Integer> number = new ArrayList<Integer>();
			for(int j=N-1;j>-1;j--) number.add(map[i][j]);
			for(int j=0;j<N;j++) newMap[j][i] = number.get(j); 
		}
		map = newMap;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			stz = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) map[i][j] = Integer.parseInt(stz.nextToken());
		}
		while(playGame(N)) {}
		System.out.println(SCORE);
	}
}