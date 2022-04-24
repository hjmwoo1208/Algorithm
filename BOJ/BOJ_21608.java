import java.util.*;
import java.io.*;
/**
 * [백준] 21608번 - 상어 초등학교
 * @author WOOHJ
 * - 시뮬레이션, 우선순위큐
 *
 */
public class BOJ_21608 {
	static class Spot implements Comparable<Spot>{
		int r,c,empty,like;
		public Spot(int r, int c, int empty, int like) {
			super();
			this.r = r;
			this.c = c;
			this.empty = empty;
			this.like = like;
		}
		@Override
		public int compareTo(Spot s) {
			if(this.like > s.like) return -1;
			else if(this.like == s.like) {
				if(this.empty > s.empty ) return -1;
				else if(this.empty == s.empty) {
					if(this.r < s.r) return -1;
					else if(this.r == s.r) {
						if(this.c < s.c) return -1;
					}
				}
			}
			return 1;
		}
	}
	static int py[] = {-1,0,1,0};
	static int px[] = {0,1,0,-1};
	
	/**
	 * 최적의 자리를 선정해주는 메소드
	 * @param N : 교실의 사이즈
	 * @param map : 교실 자리배치
	 * @param num : 탐색 학생 번호 
	 * @param info : 해당 학생의 선호하는 학생의 번호 정보
	 */
	static void find(int N, int map[][], int num, List<Integer> info) {
		PriorityQueue<Spot> pq = new PriorityQueue<Spot>();
		for(int i=1;i<=N;i++) 
			for(int j=1;j<=N;j++) {
				if(map[i][j]>0) continue;
				Spot spot = new Spot(i, j, 0, 0);
				for(int p=0;p<4;p++) {
					int y = i+py[p];
					int x = j+px[p];
					if(y<1 || x<1 || y>N || x>N) continue;
					if(map[y][x] == 0) spot.empty++;
					else
						if(info.contains(map[y][x])) spot.like++;
				}
				pq.offer(spot);
			}
		Spot pick = pq.poll();
		map[pick.r][pick.c] = num;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int stuentsCnt = N*N;
		List<Integer> studentInfo[] = new LinkedList[stuentsCnt+1];
		int classRoom[][] = new int[N+1][N+1];
		
		for(int i=0;i<stuentsCnt;i++) {
			StringTokenizer stz = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(stz.nextToken());
			studentInfo[num] = new LinkedList<Integer>();
			while(stz.hasMoreTokens()) studentInfo[num].add(Integer.parseInt(stz.nextToken()));
			find(N, classRoom, num, studentInfo[num]);
		}
		int SUM = 0;
		for(int i=1;i<=N;i++)
			for(int j=1;j<=N;j++) {
				int cnt = 0;
				for(int p=0;p<4;p++) {
					int y = i+py[p];
					int x = j+px[p];
					if(y<1 || x<1 || y>N || x>N) continue;
					if(studentInfo[classRoom[i][j]].contains(classRoom[y][x])) cnt++;
				}
				SUM += Math.pow(10, cnt-1);
			}
		System.out.println(SUM);
		
	}
}