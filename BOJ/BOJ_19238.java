import java.util.*;
import java.io.*;
public class BOJ_19238 {
	static class Passenger implements Comparable<Passenger>{
		int num, y,x, len;
		public Passenger(int num, int y, int x, int len) {
			this.num = num;
			this.y = y;
			this.x = x;
			this.len = len;
		}
		@Override
		public int compareTo(Passenger p) {
			if(this.len<p.len) return -1;
			else if(this.len==p.len) {
				if(this.y<p.y) return -1;
				else if(this.y== p.y) {
					if(this.x<p.x) return -1;
					else return 1;
				}
				return 1;
			}
			return 1;
		}
	}
	static int TOTALOIL = -1;
	static void go(int N, int M,int[] taxi, int[][] map, int passInfo[][], int oil, int cnt) {
		if(cnt ==M) {
			TOTALOIL = oil;
			return;
		}
		int py[] = {-1,0,1,0};
		int px[] = {0,1,0,-1};
		//승객들 찾기
		boolean check[][] = new boolean[N+1][N+1];
		check[taxi[0]][taxi[1]] = true;
		Queue<int[]> taxiQ = new LinkedList<int[]>();
		List<Passenger> passList = new ArrayList<Passenger>();
		taxiQ.offer(new int[] {taxi[0],taxi[1],0});
		while(!(taxiQ.isEmpty())) {
			int[] out = taxiQ.poll();
			if(map[out[0]][out[1]]>1)	passList.add(new Passenger(map[out[0]][out[1]], out[0], out[1], out[2]));
			for(int p=0;p<4;p++) {
				int y = out[0] + py[p];
				int x = out[1] + px[p];
				if(y<1 || x<1 || y>N || x>N || check[y][x] || map[y][x] == 1) continue;
				check[y][x] = true;
				taxiQ.offer(new int[] {y,x,out[2]+1});
			}
		}
		Collections.sort(passList); //정렬
		
		//목적지까지 이동하기
		if(passList.size()==0) return; //업무 종료 : 다음 손님을 태울 수 없을 때 
		Passenger target = passList.get(0); //태울 손님 정보
		oil -= target.len; 
		if(oil<= 0) return; //손님 태우러가는데 걸리는 연료가 현재 연료보다 크면 업무 종료
		
		check = new boolean[N+1][N+1];
		check[target.y][target.x] = true;
		map[target.y][target.x] = 0; //손님 태우면 지도에서 삭제
		Queue<int[]> goalQ = new LinkedList<int[]>();
		goalQ.offer(new int[] {target.y,target.x,0});
		boolean possbile = false;
		int thisOIL = 0;
		while(!(goalQ.isEmpty())) {
			int[] out = goalQ.poll();
			if(out[0]==passInfo[target.num-2][2] && out[1] ==passInfo[target.num-2][3]) {
				thisOIL = out[2];
				possbile = true;
				break;
			}
			for(int p=0;p<4;p++) {
				int y= out[0] + py[p];
				int x = out[1] + px[p];
				if(y<1 || x<1|| y>N || x>N || check[y][x] || map[y][x] ==1) continue;
				check[y][x] = true;
				goalQ.offer(new int[] {y,x,out[2]+1});
			}
		}
		if(possbile==false) return; //업무 종료 : 승객의 목적지까지 도달하지 못했을 때
		oil -= thisOIL;
		if(oil<0) return; //도착지까지 걸리는 거리가 0보다 작으면 가는 도중에 멈춘 것
		go(N,M,new int[] {passInfo[target.num-2][2],passInfo[target.num-2][3]},map,passInfo, oil+(thisOIL*2), cnt+1);
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());
		int OIL = Integer.parseInt(stz.nextToken());
		int map[][] = new int[N+1][N+1];
		for(int i=1;i<N+1;i++) {
			stz = new StringTokenizer(br.readLine());
			for(int j=1;j<N+1;j++) map[i][j] = Integer.parseInt(stz.nextToken());
		}
		int taxiStart[] = new int[2];
		stz = new StringTokenizer(br.readLine());
		taxiStart[0] = Integer.parseInt(stz.nextToken());
		taxiStart[1] = Integer.parseInt(stz.nextToken());
		int passInfo[][] = new int[M][4];
		for(int i=0;i<M;i++) {
			stz = new StringTokenizer(br.readLine());
			for(int j=0;j<4;j++) passInfo[i][j] = Integer.parseInt(stz.nextToken());
		}
		for(int i=0;i<M;i++) map[passInfo[i][0]][passInfo[i][1]] = i+2; //승객의 위치 마킹
		go(N,M,taxiStart, map, passInfo, OIL, 0);
		System.out.println(TOTALOIL);
	}//main
}