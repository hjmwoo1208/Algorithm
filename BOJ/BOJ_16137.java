import java.util.*;
import java.io.*;
public class BOJ_16137 {
	static class next implements Comparable<next>{
		int y, x, time, ojak, plusOjak; // y, x , 현재 위치까지 걸린 시간, 연속오작교 체크, 추가 오작교 사용여부
		public next(int y, int x, int time, int ojak, int plusOjak) {
			this.y = y;
			this.x = x;
			this.time = time;
			this.ojak = ojak;
			this.plusOjak = plusOjak;
		}
		@Override
		public int compareTo(next n) {
			return this.time-n.time;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());
		int map[][] = new int[N][N], MIN = Integer.MAX_VALUE;
		for(int i=0;i<N;i++) {
			stz = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) map[i][j] = Integer.parseInt(stz.nextToken());
		}
		int py[] = {-1,0,1,0};
		int px[] = {0,1,0,-1};
		PriorityQueue<next> q = new PriorityQueue<next>(); //시간순으로 
		boolean check[][][] = new boolean[N][N][2]; //[2] == 추가 오작교 사용여부
		check[0][0][0] = check[0][0][1] =  true; //출발지는 방문체크
		q.offer(new next(0, 0, 0, 0, 0)); //y,x,time,연속오작교 체크, 추가 오작교 사용여부
		while(!(q.isEmpty())) {
			next out = q.poll();
			if(out.y ==N-1 && out.x==N-1) { //직녀와 만났을 때
				if(MIN>out.time) MIN = out.time; 
				break;
			}
			for(int p=0;p<4;p++) { 
				int y = out.y + py[p];
				int x = out.x + px[p];
				if(y<0 || x<0 ||y>N-1 ||x>N-1) continue;
				if(map[y][x]==1 && check[y][x][out.plusOjak] == false) { //일반땅일 때
					q.offer(new next(y, x, out.time+1, 0, out.plusOjak));
					check[y][x][out.plusOjak] = true;
					continue;
				}
				if(map[y][x]>1 && out.ojak == 0 && check[y][x][out.plusOjak]==false) { //오작교일때
					//int time = out.time + (map[y][x] -(out.time%map[y][x])); //오작교 건널 수 있는 시간을 계산
					q.offer(new next(y, x, out.time + (map[y][x] -(out.time%map[y][x])),1, out.plusOjak));
					check[y][x][out.plusOjak] = true;
				}
				if(map[y][x] == 0 && out.ojak==0 && out.plusOjak==0 && check[y][x][1] == false) { //추가 오작교가 가능할 때
					int zero = 0;
					for(int pp=0;pp<4;pp++) { //교차로인지 체크
						int nextY = y+py[pp];
						int nextX = x+ px[pp];
						if(nextY<0 || nextX <0 || nextY>N-1 || nextX > N-1) continue;
						if(map[nextY][nextX] == 0) zero++;
					}
					if(zero>2) continue;  // 교차 절벽은 오작교 불가
					check[y][x][1] = true;
//					int time = out.time + (M-(out.time%M));
					q.offer(new next(y, x, out.time + (M-(out.time%M)), 1, 1)); //추가 오작교 사용여부 추가해서 offer
				}
			}
		}
		System.out.println(MIN);
	}
}