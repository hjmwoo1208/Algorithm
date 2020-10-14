/* 풀이 방식 ********************************************
 * 1. 각 얼음이 녹는 날을 구한다.
 * 2. 백조가 다른 백조를 찾으러 가는 BFS에 거쳐가는 빙산의 녹는 날짜를 담아간다.
 * 3. 우선순위 큐를 사용해서 빙산의 녹는 날짜가 작은 루트를 먼저 이동시킨다.
 * ****************************************************/
import java.util.*;
import java.io.*;
public class BOJ_3197 {
	static class move implements Comparable<move>{ //백조가 움직일 때 쓸 객체
		int y, x, time;
		public move(int y, int x, int time) {
			super();
			this.y = y;
			this.x = x;
			this.time = time;
		}
		@Override
		public int compareTo(move o) {
			return this.time - o.time; //거쳐오는 빙하가 녹는 시간이 작은 순으로 정렬
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(stz.nextToken());
		int C = Integer.parseInt(stz.nextToken());
		String[] map = new String[R];
		for(int i=0;i<R;i++) map[i] = br.readLine();
		
		//백조의 위치를 구함.
		int L[] = new int[4];
		Arrays.fill(L, -1);
		findL :
		for(int i=0;i<R;i++)
			for(int j=0;j<C;j++) 
				if(map[i].charAt(j) == 'L') { 
					if(L[0] == -1) {
						L[0] = i;
						L[1] = j;
					}else {
						L[2] = i;
						L[3] = j;
						break findL;
					}
				}
		
		boolean visited[][] = new boolean[R][C]; //빙산이 녹는 날짜, 백조가 이동하는 BFS에 사용하는 방문 마킹
		int waterTime[][] = new int[R][C]; //빙산이 녹는 날짜를 저장
		int py[] = {-1,0,1,0}, px[] = {0,1,0,-1}, time = 0; // 상우하좌 , time = 만나는 최소날짜

		// 빙산이 녹는 날짜를 구하는 과정
		// 1. 첫날에 녹는 가장자리 빙산들을 찾는다.
		Queue<int[]> q = new LinkedList<int[]>();
		for(int i=0;i<R;i++)
			for(int j=0;j<C;j++)
				if(map[i].charAt(j)=='X') 
					for(int p=0;p<4;p++) {
						int y = i+py[p];
						int x = j+px[p];
						if(y<0 ||x<0|| y>R-1 || x>C-1) continue;
						if(map[y].charAt(x)!='X') {
							q.offer(new int[] {i,j});
							visited[i][j] = true;
							waterTime[i][j] = 1;
							break;
						}
					}
		
		// 2. 가장자리를 시작으로 빙산이 녹는 날짜를 구한다.
		while(!(q.isEmpty())) { 
			int out[] = q.poll();
			for(int p=0;p<4;p++) {
				int y = out[0] +py[p];
				int x = out[1] +px[p];
				if(y<0 ||x<0|| y>R-1 || x>C-1) continue;
				if(map[y].charAt(x)=='X' && visited[y][x] ==false){
					q.offer(new int[] {y,x});
					waterTime[y][x] = waterTime[out[0]][out[1]]+1; //다음 빙산 = 현재 빙산이 녹는 날짜 + 1일 
					visited[y][x] = true; //방문 표시
				}
			}
		}
		
		//3. 하나의 백조의 위치로 BFS를 해서 또다른 백조를 찾는다.
		visited = new boolean[R][C]; //백조가 이동하는 BFS에서 사용할 visited배열 초기화
		PriorityQueue<move> pq = new PriorityQueue<move>(); // 거쳐온 빙산이 녹는 날짜가 작은 순으로 
		pq.offer(new move(L[0], L[1], 0)); 
		while(!(pq.isEmpty())) {
			move out = pq.poll();
			if(out.y == L[2] && out.x==L[3]) { //또다른 백조를 만났을 때
				time = out.time; //우선순위 큐를 사용했기 때문에 처음 또다른 백조를 만났을 때가 최단 시간이다.
				break; //우선순위 큐를 사용해서 최단시간을 구했으므로 더이상 볼 필요 없다.
			}
			for(int p=0;p<4;p++) {
				int y = out.y+py[p];
				int x = out.x+px[p];
				if(y<0 ||x<0|| y>R-1 || x>C-1 ||visited[y][x]) continue;
				//다음에 지나갈 곳의 녹는 시간이 현재 지나온 빙산이 녹는 시간보다 길다면 지나쳐온 빙산의 최대시간을 바꿔준다.
				if(waterTime[y][x] > out.time) pq.offer(new move(y, x, waterTime[y][x]));
				else pq.offer(new move(y, x, out.time)); //다음 지나갈 곳의 녹는 시간이 현재 지나온 녹는시간(바다포함)보다 작으면 현재 최대 시간을 그대로 가져간다.
				visited[y][x] = true;
			}
		}
		System.out.println(time);
	}
}