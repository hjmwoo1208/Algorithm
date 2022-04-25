import java.util.*;
import java.io.*;
/**
 * [백준] 23288번 - 주사위 굴리기 2
 * @author WOOHJ
 * 
 */

public class BOJ_23288 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());
		int play = Integer.parseInt(stz.nextToken());
		int board[][][] = new int[2][N][M]; // [0][i][j] : 지도 , [1][i][j] : 그룹 크기 저장
		for(int i=0;i<N;i++) {
			stz = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) board[0][i][j] = Integer.parseInt(stz.nextToken());
		}
		int xy[] = {0,0,1}; // y,x,p
		int py[] = { -1, 0, 1, 0}; // 상우하좌 
		int px[] = { 0 , 1, 0, -1}; 
		int dice[] = { 2,4,1,3,5,6 }; // (윗면 기준) 위, 좌, [윗면], 우측, 아래, [아랫면]
		
		// [북, 동, 남, 서 ] 순으로 주사위를 방향으로 돌릴때 바뀌는 주사위의 인덱스 -> 돌리면 한칸씩 SHIFT
		int diceIndex[][] = {{4,2,0,5},{1,2,3,5},{0,2,4,5},{3,2,1,5}};
	
		int SCORE = 0;
		for(int turn=0;turn<play;turn++) {
			// 1-1. xy[2] 방향으로 한칸 이동
			int y = xy[0] + py[xy[2]];
			int x = xy[1] + px[xy[2]];
			if(y<0 || x<0 || y>=N || x>=M) {
				xy[2] = (xy[2]+2)%4;
				y = xy[0] + py[xy[2]];
				x = xy[1] + px[xy[2]];
			}
			xy = new int[] { y, x, xy[2] };
			// 1-2. xy[2] 방향으로 주사위 돌리기 
			List<Integer> number = new ArrayList<Integer>();
			for(int i=0;i<4;i++) number.add(dice[diceIndex[xy[2]][i]]);
			for(int i=0;i<4;i++) dice[diceIndex[xy[2]][(i+1)%4]] = number.get(i);
			
			// 2. 점수 구하기
			if(board[1][xy[0]][xy[1]]>0) {
				SCORE += (board[0][xy[0]][xy[1]] * board[1][xy[0]][xy[1]]);
				if(turn == play-1) break; // 마지막 턴이면 점수만 구하고 끝낸다
			}else { // 처음 탐색
				List<int[]> group = new ArrayList<int[]>();
				boolean visited[][] = new boolean[N][M];
				group.add(new int[] {xy[0],xy[1]});
				visited[xy[0]][xy[1]] = true;
				int pivot = 0;
				while(pivot < group.size()) {
					int presentXY[] = group.get(pivot);
					pivot++;
					for(int p=0;p<4;p++) {
						int nextY = presentXY[0] + py[p];
						int nextX = presentXY[1] + px[p];
						if(nextY <0 || nextX < 0 || nextY >= N || nextX >= M || visited[nextY][nextX] || board[0][nextY][nextX] != board[0][xy[0]][xy[1]]) continue;
						group.add(new int[] {nextY, nextX});
						visited[nextY][nextX] = true;
					}
				}
				int size = group.size();
				SCORE += ( board[0][xy[0]][xy[1]] * size);
				if(turn == play-1) break; // 마지막 턴이면 점수만 구하고 끝낸다
				for(int g[] : group) board[1][g[0]][g[1]] = size; // 그룹에 해당하는 위치에 크기를 저장해둔다.
			}
			// 3. board[0][i][j] 와 주사위 아랫면 비교 dice[5] + xy[2] 방향 바꾸기
			if(dice[5] > board[0][xy[0]][xy[1]] ) xy[2] = (xy[2] + 1)%4; // 주사위 아랫면 > 주사위가 위치한 지도의 숫자 -> 시계방향으로 90도 회전
			else if(dice[5] < board[0][xy[0]][xy[1]]) xy[2] = (xy[2] + 3)%4; // 주사위 아랫면 < 주사위가 위치한 지도의 숫자 -> 반시계방향으로 90도 회전
		}
		System.out.println(SCORE);
	}
}