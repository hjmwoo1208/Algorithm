import java.util.*;
import java.io.*;
public class BOJ_16957 {
/* 체스판 위의 공 - 성공
 * !!!! 시간초과 주의 !!!!!
 * - DP로 푸는 방법도 있을 것 같으나...우선순위 큐로 풀음
 * - 작은 값부터 우선순위 큐 -> 70% 시간초과
 * - 큰 값부터 우선순위 큐 -> 성공
 * 		풀이 1. Queue를 바꿔가며 한턴씩 공이 이동한다.
 * 		풀이 2. 하나의 Queue를 사용하여 큰 숫자칸의 있는 공이 끝까지 움직인 뒤 다음 공이 움직인다. (DFS 비슷)
 * 
 *  */
	static class Ball implements Comparable<Ball>{
		int y, x, num;
		public Ball(int y, int x, int num) {
			super();
			this.y = y;
			this.x = x;
			this.num = num;
		}
		@Override
		public int compareTo(Ball b) {
			return b.num - this.num; //큰 값부터 우선순위 큐를 진행해야한다.
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(stz.nextToken());
		int C = Integer.parseInt(stz.nextToken());
		int chess[][] = new int[R][C], count[][]  = new int[R][C];
		PriorityQueue<Ball> q = new PriorityQueue<>();
		for(int i=0;i<R;i++) {
			stz = new StringTokenizer(br.readLine());
			for(int j=0;j<C;j++) {
				chess[i][j] = Integer.parseInt(stz.nextToken());
				count[i][j] = 1;
				q.offer(new Ball(i,j,chess[i][j]));
			}
		}
		boolean stop[][] = new boolean[R][C];
		int py[] ={-1,-1,0,1,1,1,0,-1}, px[] = {0,1,1,1,0,-1,-1,-1};
		
		while(!q.isEmpty()) {
			int size = q.size();
//			PriorityQueue<Ball> tempQ = new PriorityQueue<>(); //풀이 1. Queue를 두개 사용해서 각각의 공이 한턴씩 움직이는 방법
			for(int s=0;s<size;s++) {
				Ball out	 = q.poll();
				if(stop[out.y][out.x] || count[out.y][out.x] == 0) continue;
				int min[] = {-1, 300003};
				for(int p=0;p<8;p++) { //8방향 중 작은 값을 고름
					int y = out.y +py[p];
					int x = out.x + px[p];
					if(y<0 || x<0 || y>=R || x>=C ) continue;
					if(chess[y][x] < chess[out.y][out.x] && min[1]>chess[y][x]) min = new int[] {p,chess[y][x]};
				}
				if(min[0]==-1) { //내가 제일 작은 경우 멈춤
					stop[out.y][out.x] = true;
				}else {
					int y = out.y + py[min[0]];
					int x = out.x + px[min[0]];
//					if(!stop[y][x]) tempQ.offer(new Ball(y,x,chess[y][x])); //다음 칸이 stop이 아닐 때만 계속 진행
					if(!stop[y][x]) q.offer(new Ball(y,x,chess[y][x]));
					count[y][x] += count[out.y][out.x]; //다음 칸에 공을 쌓기
					count[out.y][out.x] = 0;
				}
			}
//			q = tempQ; //우선순위 큐에 같은 숫자만 계속 돌지 않도록 Queue를 바꿔준다
		}
		for(int i=0;i<R;i++) {
			StringBuilder sb = new StringBuilder();
			for(int j=0;j<C;j++) sb.append(count[i][j]+" ");
			System.out.println(sb);
		}
	}
}