import java.util.*;
/**
 * [백준] 23291번 - 어항 정리
 * @author WOOHJ
 *
 */
public class BOJ_23291 {
	static class MinFish implements Comparable<MinFish>{
		int x, size;
		public MinFish(int x, int size) {
			super();
			this.x = x;
			this.size = size;
		}
		@Override
		public int compareTo(MinFish o) {
			return this.size - o.size;
		}
	}
	static class Fish implements Comparable<Fish>{
		int y, x, size;
		public Fish(int y, int x, int size) {
			super();
			this.y = y;
			this.x = x;
			this.size = size;
		}
		@Override
		public int compareTo(Fish f) {
			return f.size - this.size;
		}
	}
	static int[][] turn90 (int array[][]) {
		int h = array.length;
		int w = array[0].length;
		
		int result[][] = new int[w+1][h];
		for(int j=0;j<w;j++) {
			List<Integer> num = new ArrayList<Integer>();
			for(int i=h-1;i>=0;i--) num.add(array[i][j]);
			for(int i=0;i<h;i++) result[j][i] = num.get(i);
		}
		return result;
	}
	static int[][] turnOne(int fishBowl[], int N) {
		int left[][] = {{fishBowl[0]}};
		int right[] = new int[N-1];
		int lh = 1;
		int lw = 1;
		int rw = N-1;
		System.arraycopy(fishBowl, 1, right, 0, N-1);
		while(true) {
			left = turn90(left);
			lh = left.length;
			lw = left[0].length;
			System.arraycopy(right, 0, left[lh-1], 0, lw);
			rw = right.length - lw;
			if(rw == 0) break;
			int newRight[] = new int[rw];
			System.arraycopy(right, lw, newRight, 0, rw);
			right = newRight;
			if(lh > rw) break;
		}
		int arr[][] = new int[lh][lw+rw];
		for(int i=0;i<lh;i++) 
			System.arraycopy(left[i], 0, arr[i], 0, lw);
		if(rw > 0)
			System.arraycopy(right, 0, arr[lh-1], lw, rw);
		return arr;
	}
	
	static int[][] turnTwo(int fishBowl[], int N){
		int w = N/2;
		int result[][] = new int[2][w];
		System.arraycopy(fishBowl, w, result[1], 0, w);
		for(int i=0;i<w;i++) result[0][i] = fishBowl[w-1-i];
		w = w/2;
		int result2[][] = new int[4][w];
		System.arraycopy(result[0], w, result2[2], 0, w);
		System.arraycopy(result[1], w, result2[3], 0, w);
		for(int i=0;i<w;i++) {
			result2[0][i] = result[1][w-1-i];
			result2[1][i] = result[0][w-1-i];
		}
		return result2;
	}
	
	static int[] makeLinear(int board[][], int N) {
		List<Integer> numbers =  new ArrayList<Integer>();
		int h = board.length;
		int w = board[0].length;
		for(int j=0;j<w;j++)
			for(int i=h-1;i>=0;i--)
				if(board[i][j] > 0) numbers.add(board[i][j]);
		int linear[] = new int[N];
		for(int i=0;i<N;i++) linear[i] = numbers.get(i);
		return linear;
	}
	
	static int py[] = {-1,0,1,0};
	static int px[] = {0,1,0,-1};
	static int[][] moveFish(int turn[][]) {
		int hight = turn.length;
		int width = turn[0].length;
		PriorityQueue<Fish> pq = new PriorityQueue<Fish>();
		for (int i = 0; i < hight; i++)
			for (int j = 0; j < width; j++)
				if (turn[i][j] > 0) pq.add(new Fish(i, j, turn[i][j]));

		int move[][] = new int[hight][width];
		while (!pq.isEmpty()) {
			Fish out = pq.poll();
			int sum = 0;
			for (int p = 0; p < 4; p++) {
				int y = out.y + py[p];
				int x = out.x + px[p];
				if (y < 0 || x < 0 || y >= hight || x >= width || turn[y][x] == 0 || turn[y][x] > out.size) continue;
				int moveCnt = (out.size - turn[y][x]) / 5;
				move[y][x] += moveCnt;
				sum += moveCnt;
			}
			move[out.y][out.x] += (out.size - sum);
		}
		return move;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer stz = new StringTokenizer(sc.nextLine());
		int N = Integer.parseInt(stz.nextToken());
		int K = Integer.parseInt(stz.nextToken());
		stz = new StringTokenizer(sc.nextLine());
		int fishBowl[] = new int[N];
		for(int i=0;i<N;i++) fishBowl[i] = Integer.parseInt(stz.nextToken());
		sc.close();
		
		int CNT = 0;		
		while(true) {
			
			// 차이 구하기
			int min = fishBowl[0], max = fishBowl[0];
			for(int i=1;i<N;i++) {
				min = Math.min(min, fishBowl[i]);
				max = Math.max(max, fishBowl[i]);
			}
			if((max-min) <= K ) break;
			
			// 제일 적은 어항에 
			List<int[]> list = new ArrayList<int[]>();
			for(int i=0;i<N;i++) list.add(new int[] {i,fishBowl[i]});
			Collections.sort(list, (f1 , f2) -> {
				return f1[1] - f2[1];
			});
			int MIN = list.get(0)[1];
			fishBowl[list.get(0)[0]]++;
			for(int i=1;i<N;i++) {
				if(list.get(i)[1] > MIN) break;
				if(list.get(i)[1] == MIN) fishBowl[list.get(i)[0]]++;
			}
			
			// 왼쪽-> 오른쪽 쌓기
			int turn[][] = turnOne(fishBowl,N);
			
			//물고기 이동
			int move[][] = moveFish(turn);
			
			// 펼치기
			int linear[] = makeLinear(move, N);
			
			// 시계방향 180 접기
			turn = turnTwo(linear, N);
			
			// 물고기 이동
			move = moveFish(turn);
			
			//펼치기
			fishBowl = makeLinear(move, N);
			CNT++;
		}
		System.out.println(CNT);
	}

}
