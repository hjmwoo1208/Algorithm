import java.util.*;
public class 퍼즐_조각_채우기 {
	static class Puzzle{
		int cnt; //퍼즐의 수
		int setting[][]; //퍼즐의 모양
		int len[] = new int[2]; //y,x 길이
		boolean check = false; //사용여부
		public Puzzle() {}
		public Puzzle(int cnt, int[][] setting) {
			super();
			this.cnt = cnt;
			this.setting = setting;
			len[0] = setting.length;
			len[1] = setting[0].length;
		}
		void rotation () { // 회전
			int ylen = setting.length;
			int xlen = setting[0].length;
	
			int temp[][] = new int[xlen][ylen];
			
			for(int i=0;i<ylen;i++) {
				int sub[] = setting[i];
				for(int j=0;j<xlen;j++) temp[j][ylen-1-i] = sub[j];
			}
			
			setting = temp;
			len[0] = temp.length;
			len[1] = temp[0].length;
		}
	}
	
	static List<Puzzle> blockList[] = new ArrayList[7]; //각 블럭 갯수별 puzzle 
	static boolean visited[][];
	
	// game_board의 빈공간, table의 퍼즐 찾는 메소드
	static int[] find(int num, int[][] board, int length, int[] start) {
		int py[] = {-1,0,1,0}, px[] = {0,1,0,-1};
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {start[0],start[1]});
		visited[start[0]][start[1]] = true;
		
		int cnt = 1;
		int min[] ={start[0],start[1]};
		int max[] ={start[0],start[1]};
		
		while(!q.isEmpty()) {
			int out[] = q.poll();
			for(int p=0;p<4;p++) {
				int y = out[0] +py[p];
				int x = out[1] + px[p];
				if(y<0 || x<0 || y>=length || x>=length || visited[y][x] || board[y][x] != num) continue;
				
				visited[y][x] = true;
				q.offer(new int[] {y,x});
				
				//원하는 숫자가 있는 좌표의 최댓값과 최솟값을 구한다 -> 최솟값과 최댓값의 차로 모양의 행, 열 사이즈를 구함
				min[0] = Math.min(min[0], y);
				min[1] = Math.min(min[1], x);
				max[0] = Math.max(max[0], y);
				max[1] = Math.max(max[1], x);
				cnt ++;
			}
		}
		
		return new int[] {cnt,min[0],min[1],max[0],max[1]}; // cnt, min y, min x, max y, max x
		
	}
	
    public static int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        
        int length = game_board.length;  
        for(int i=0;i<7;i++) blockList[i] = new ArrayList<Puzzle>();
        
        // table에서 퍼즐 찾기
        visited = new boolean[length][length];
        for(int i=0;i<length;i++) 
        	for(int j=0;j<length;j++) 
        		if(table[i][j] == 1 && !visited[i][j]) {
        			int result[] = find(1, table, length, new int[] {i,j});
        			
        			int ylen = result[3] - result[1] +1;
        			int xlen = result[4] - result[2] +1;
        			
        			int setting[][] = new int[ylen][xlen];
        			for(int t=0;t<ylen;t++) System.arraycopy(table[ result[1]+t], result[2], setting[t], 0, xlen);
        			blockList[result[0]].add(new Puzzle(result[0], setting));
        		}
        
        // game_board에서 빈공간 찾기
        visited = new boolean[length][length];
        for(int i=0;i<length;i++) 
        	for(int j=0;j<length;j++) 
        		if(game_board[i][j] == 0 && !visited[i][j]) {
        			//빈공간 모양 탐색
        			int result[] = find(0, game_board, length, new int[] {i,j});
        			
        			//빈공간의 직사각형 사이즈
        			int ylen = result[3] - result[1] +1;
        			int xlen = result[4] - result[2] +1;
        			
        			
        			//빈공간의 갯수와 같은 퍼즐이 존재한다면 탐색
        			if(!blockList[result[0]].isEmpty()) {
        				int blockListLength = blockList[result[0]].size();
        				
        				block :
        				for(int b=0;b<blockListLength;b++) {
        					Puzzle puzzle = blockList[result[0]].get(b);
        					if(puzzle.check) continue; // 이미 사용한 퍼즐은 패스
        					
        					// 퍼즐과 일치할 가능성이 있을 때
        					if((puzzle.len[0] ==ylen && puzzle.len[1] == xlen)||(puzzle.len[0]==xlen&&puzzle.len[1]==ylen)){
        						for(int c=0;c<4;c++) {
        							if(puzzle.len[0] ==ylen && puzzle.len[1] == xlen) { 
        								int setting[][] = puzzle.setting;
        								boolean flag = false;
        								add :
        								for(int y=0;y<ylen;y++)
        									for(int x=0;x<xlen;x++)
        										// game_board의 빈공간(0) + table의 퍼즐(1) = 1 이 아니면 일치하지 않는 퍼즐이므로 탐색 종료
        										if(setting[y][x] + game_board[result[1]+y][result[2]+x] != 1) {
        											flag = true;
        											break add;
        										}
        								if(!flag) { //일치
        									answer+=result[0]; // 채워진 공간 만큼 answer 업데이트
        									blockList[result[0]].get(b).check = true; //퍼즐 사용 유무 업데이트
        									break block; // 퍼즐 리스트 탐색 종료
        								}
        						}
        						puzzle.rotation(); // 가로, 세로가 일치하지 않거나 현재 모양의 퍼즐과 일치하지 않으면 퍼즐을 돌린다.
        					}
        				}
        			}
        		}
        	}//if

        return answer;
    }
	public static void main(String[] args) {
		System.out.println(solution(new int[][] {{1,1,0,0,1,0},{0,0,1,0,1,0},{0,1,1,0,0,1},{1,1,0,1,1,1},{1,0,0,0,1,0},{0,1,1,1,0,0}}, 
				new int[][] {{1,0,0,1,1,0},{1,0,1,0,1,0},{0,1,1,0,1,1},{0,0,1,0,0,0},{1,1,0,1,1,0},{0,1,0,0,0,0}}));
		System.out.println(solution(new int[][] {{0,0,0},{1,1,0},{1,1,1}} , new int[][] {{1,1,1},{1,0,0},{0,0,0}}));
	}
}