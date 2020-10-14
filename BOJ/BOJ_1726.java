import java.util.*;
import java.io.*;
public class BOJ_1726 {
	static class Robot implements Comparable<Robot>{
		int y, x, p, cnt;
		public Robot(int y, int x, int p, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.p = p;
			this.cnt = cnt; //명령 횟수
		}
		@Override
		public int compareTo(Robot o) { //명령어의 횟수가 작은 순으로
			return this.cnt-o.cnt;
		}
	}
	static int[] dirChange(int[] original) { //상우하좌 순서로 바꿔주는 메소드
		switch (original[2]) {
		case 4 : original[2] = 0; break;
		case 1 : original[2] = 1; break;
		case 3 : original[2] = 2; break;
		case 2 : original[2] = 3; break;
		}
		return original;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());
		int map[][] = new int[N+1][M+1], position[][] = new int[2][]; //position[0] = 출발 정보 , position[1] = 도착정보
		for(int i=1;i<N+1;i++) {
			stz=new StringTokenizer(br.readLine());
			for(int j=1;j<M+1;j++) map[i][j] = Integer.parseInt(stz.nextToken());
		}
		for(int i=0;i<2;i++) {
			stz = new StringTokenizer(br.readLine());
			position[i] = new int[] {Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken())};
			position[i] = dirChange(position[i]); //상우하좌 순서로 바꿔준다.
		}
		boolean check[][][] = new boolean[4][N+1][M+1]; //로봇 방향, y, x
		int py[] = {-1,0,1,0}, px[] = {0,1,0,-1}, MIN = 0; //상우하좌
		PriorityQueue<Robot> q = new PriorityQueue<Robot>();
		check[position[0][2]][position[0][0]][position[0][1]] = true; //출발 위치(방향, y,x) 마킹
		q.offer(new Robot(position[0][0],position[0][1], position[0][2], 0)); //BFS 시작
		while(!(q.isEmpty())) {
			Robot out = q.poll();
			if(out.y==position[1][0] && out.x ==position[1][1]  && out.p==position[1][2] ) { //목적지에 도달했으면
				MIN = out.cnt; //우선순위 큐를 사용했기때문에 최초 도착이 최소 명령
				break;
			}
			for(int k=1;k<4;k++) { //Go k : k=1~3
				//범위가 넘으면 다음 이동 볼 필요없음
				if(out.y+py[out.p]*k<1 || out.y+py[out.p]*k>N || out.x+px[out.p]*k<1 ||out.x+px[out.p]*k>M) break;
				//중간에 장애물이면 다음 이동 볼 필요없음
				if(map[out.y+py[out.p]*k][out.x+px[out.p]*k]==1) break;
				//방문한적 없다면
				if(check[out.p][out.y+py[out.p]*k][out.x+px[out.p]*k]==false) {
					check[out.p][out.y+py[out.p]*k][out.x+px[out.p]*k]=true;
					q.offer(new Robot(out.y+py[out.p]*k, out.x+px[out.p]*k, out.p, out.cnt+1));
				}
			}
			for(int turn=1;turn<4;turn+=2) //turn =1 : 오른쪽 , turn=3 : 왼쪽
				if(check[(out.p+turn)%4][out.y][out.x] == false) { //다음 명령의 방향과 위치에 방문한적 없다면
					check[(out.p+turn)%4][out.y][out.x] = true;
					q.offer(new Robot(out.y, out.x, (out.p+turn)%4, out.cnt+1));
				}
		}
		System.out.println(MIN);
	}//main
}