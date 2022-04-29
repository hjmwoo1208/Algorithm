import java.util.*;
import java.io.*;
/**
 * [백준] 23290번 - 마법사 상어와 복제
 * @author WOOHJ
 * - 시뮬레이션
 * - 백트래킹
 * - 탐색
 * ==> JAVA 숏코드 1등 *^^*
 */
public class BOJ_23290 {
	static class Shark implements Comparable<Shark>{
		int y, x, fish, cnt, route;
		public Shark(int y, int x, int fish, int cnt, int route) {
			super();
			this.y = y;
			this.x = x;
			this.fish = fish;
			this.cnt = cnt;
			this.route = route;
		}
		@Override
		public int compareTo(Shark o) {
			return this.fish - o.fish;
		}
	}
	static int[] max = new int[2]; // 이동경로, 최댓값
	static int sy[] = { 0, -1, 0, 1, 0};
	static int sx[] = { 0, 0, -1, 0, 1};
	
	static void findMax(int count[][], int y, int x,int fish, int cnt, int route) {
		if(cnt == 3) {
			// 아무것도 먹지 못했을 경우가 있으므로 사전순 맨앞 루트를 초기값으로 저장한다.
			if(max[0] == 0 ) {
				max = new int[] {route,fish};
				return;
			}
			// 잡아먹은 물고기 갯수가 작으면 패스
			if(max[1] > fish) return;
			if(max[1] < fish) max = new int[] {route,fish};
			return;
		}
		for(int p=1;p<=4;p++) {
			int ny = y + sy[p];
			int nx = x + sx[p];
			if(ny < 0 || nx < 0 || ny >=4 || nx >= 4) continue;
			int fishCnt = count[ny][nx];
			count[ny][nx] = 0;
			findMax(count, ny, nx, fish+fishCnt, cnt+1, route*10+p);
			// 잡아먹은 물고기 원복
			count[ny][nx] = fishCnt;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(stz.nextToken());
		int S = Integer.parseInt(stz.nextToken());
		
		List<int[]> fishs = new ArrayList<int[]>();
		for(int i=0;i<M;i++) {
			stz = new StringTokenizer(br.readLine());
			fishs.add(new int[] {Integer.parseInt(stz.nextToken())-1,Integer.parseInt(stz.nextToken())-1,Integer.parseInt(stz.nextToken())-1});
		}
		stz = new StringTokenizer(br.readLine());
		int shark[] = new int[] {Integer.parseInt(stz.nextToken())-1,Integer.parseInt(stz.nextToken())-1};
		
		int fy[] = {  0,-1,-1,-1,0,1,1,1 };
		int fx[] = { -1,-1,0,1,1,1,0,-1};
		
		Queue<int[]> smellList = new LinkedList<int[]>(); // 냄새 정보 저장 (y,x,삭제 시간)
		boolean smell[][] = new boolean[5][5]; // 냄새가 존재하는 격자인지 체크
		for(int cnt=1;cnt<=S;cnt++) {
			// 물고기 복제
			List<int[]> copy = new ArrayList<int[]>(fishs);
			fishs = new ArrayList<int[]>();	// 이동한 물고기 정보 저장 (y,x,방향)
			
			// 모든 물고기 이동
			int fishCount[][] = new int[5][5]; // 격자칸안에 있는 물고기의 수 저장
			for(int[] fish : copy) {
				boolean flag = false;
				int p = fish[2];
				for(int t=0;t<8;t++) {
					int y = fish[0] + fy[p];
					int x = fish[1] + fx[p];
					if(y<0 || x<0 || y >= 4 || x>=4 || smell[y][x] || (shark[0] == y && shark[1] == x)) {
						p = (p+7)%8; // 반시계방향으로 45도 회전
						continue;
					}
					fishs.add(new int[] {y,x,p});
					flag = true;
					fishCount[y][x]++;
					break;
				}
				// 이동하지 못했을 때 - 그대로 위치를 저장하고 카운트한다.
				if(!flag) {
					fishs.add(new int[] {fish[0],fish[1],fish[2]});
					fishCount[fish[0]][fish[1]]++;
				}
			}
			
			// 상어 연속 3칸 이동
			max = new int[2]; // 이동 경로, 최댓값
			findMax(fishCount, shark[0], shark[1],0,0,0); // 이동경로 탐색
			
			boolean sharkRoute[][] = new boolean[5][5]; // 상어의 이동경로 체크하는 격자
			int tmp[] = new int[] {shark[0],shark[1]};
			String str = String.valueOf(max[0]);
			for(int i=0;i<3;i++) {
				int p = str.charAt(i) - '0';
				tmp[0] += sy[p];
				tmp[1] += sx[p];
				sharkRoute[tmp[0]][tmp[1]] = true;
			}
			shark = new int[] {tmp[0],tmp[1]}; // 상어의 위치 갱신
			
			List<int[]> lastFish = new ArrayList<int[]>(); // 살아남은 물고기 정보 저장
			for(int[] fish : fishs) {
				if(sharkRoute[fish[0]][fish[1]]) { // 상어가 지나간 자리에 위치한 물고기는 저장하지 않는다.
					smell[fish[0]][fish[1]] = true; // 물고기 냄새 마킹 
					smellList.offer(new int[] {fish[0],fish[1],cnt+2}); // 물고기 위치, 사라지는 시간 저장
					continue; 
				}
				lastFish.add(fish); // 살아남은 물고기
			}
			fishs = lastFish; // 물고기 정보 갱신
			
			// 2회 전 물고기 냄새 삭제
			int size = smellList.size();
			for(int i=0;i<size;i++) {
				if(cnt < smellList.peek()[2]) break;
				smellList.poll();
			}
			
			// 삭제된 냄새 정보로 냄새 갱신
			smell = new boolean[5][5];
			for(int sl[]: smellList) smell[sl[0]][sl[1]] = true;
			
			// 복제 마법
			for(int c[] : copy) fishs.add(c);
		}
		System.out.println(fishs.size());
	}
}