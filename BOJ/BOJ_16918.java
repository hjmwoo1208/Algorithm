import java.io.*;
import java.util.*;

public class BOJ_16918 {
	static class Bomb implements Comparable<Bomb>{
		int y,x,time;
		public Bomb(int y, int x, int time) {
			super();
			this.y = y;
			this.x = x;
			this.time = time;
		}
		@Override
		public int compareTo(Bomb b) {
			return this.time - b.time;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(stz.nextToken());
		int C = Integer.parseInt(stz.nextToken());
		int N = Integer.parseInt(stz.nextToken());
		
		PriorityQueue<Bomb> bq = new PriorityQueue<>(); //폭탄이 터지는 시간 기준 우선순위 큐
		String map[] = new String[R];
		int	 time[][] = new int[R][C]; //폭탄이 터지는 시간을 마킹  ( 0이면 빈 곳 )
	
		for(int i=0;i<R;i++) {
			map[i] = br.readLine();
			for(int j=0;j<C;j++) 
				if(map[i].charAt(j) == 'O'){
					bq.offer(new Bomb(i, j, 3));
					time[i][j] = 3;
				}
		}
		
		int py[] = {-1,0,1,0}, px[] = {0,1,0,-1} , t=2; //처음 1초일 때는 아무 것도 안한므로 while문 실행하지 않도록 2초부터 시작
		while(t<=N) { //2초일 때는 실행하지 않음
			if(t%2==0) { // 짝수 초일 때는 폭탄을 설치한다.
				for(int i=0;i<R;i++)
					for(int j=0;j<C;j++)
						if(time[i][j] == 0) {
							bq.offer(new Bomb(i, j, t+3));
							time[i][j] = t+3	; // 터지는 시간을 마킹
						}
			}else { // 홀수초일 때 폭탄이 터진다.
				while(!(bq.isEmpty())) {
					if(bq.peek().time>t) break; //  queue에서 꺼낸 폭탄의 시간이 현재 시간보다 크면 Queue안의 아이템의 시간들은 현재시간보다 크다
					Bomb out = bq.poll();
					if(out.time != time[out.y][out.x]) continue; //지도에 표시된 폭탄이 터지는 시간과 Queue에서 나온 time이 같지 않으면 이미 터진 폭탄
					for(int p=0;p<4;p++) {
						int y = out.y + py[p];
						int x = out.x + px[p];
						if(y<0 || x<0 || y>=R || x>=C || time[y][x] == out.time) continue; //경계값은 넘긴다.
						time[y][x] = 0; //폭탄이 터진 곳은 0으로 변환
					}
					time[out.y][out.x] = 0; //폭탄 터진 곳
				}
			}
			t++; // 시간 증가
		}
		
		for(int i=0;i<R;i++) {
			StringBuilder sb = new StringBuilder(); //시간초과 방지하기 위해서 StringBuilder를 사용한다.
			for(int j=0;j<C;j++) {
				if(time[i][j] == 0 ) sb.append('.'); // time이 0인 곳은 현재 빈 곳
				else sb.append('O'); // 0이 아니면 폭탄이 있는 곳
			}
			System.out.println(sb);
		}
	}
}