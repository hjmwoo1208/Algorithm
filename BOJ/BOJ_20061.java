import java.util.*;
import java.io.*;
/* 모노미노도미노2 - 성공
 * <풀이방법>
 * 블럭의 3종류를 초록색 보드, 파란색 보드에서 움직일 때를 따로 구현한다.
 * <주의사항>
 *  !! 행이나 열이 타일로 가득찬 경우와 연한 칸에 블록이 있는 경우가 동시에 발생할 수 있다. 
 *  이 경우에는 행이나 열이 타일로 가득 찬 경우가 없을 때까지 점수를 획득하는 과정이 모두 진행된 후, 연한 칸에 블록이 있는 경우를 처리해야 한다.
 * */
public class BOJ_20061 {
	static boolean[][] blueRemove(boolean blue[][], int index){
		for(int j=index;j>0;j--) 
			for(int i=0;i<4;i++) blue[i][j] =blue[i][j-1];
		for(int i=0;i<4;i++) blue[i][0] = false;
		return blue;
	}
	static boolean[][] greenRemove(boolean green[][], int index){
		for(int i=index;i>0;i--) System.arraycopy(green[i-1], 0, green[i], 0, 4);
		Arrays.fill(green[0], false);
		return green;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()), score = 0;
		boolean green[][] = new boolean[6][4], blue[][] = new boolean[4][6];
		for(int i=0;i<N;i++) {
			StringTokenizer stz = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(stz.nextToken());
			int y = Integer.parseInt(stz.nextToken());
			int x = Integer.parseInt(stz.nextToken());
			
			if(t==1) { //1x1
				//blue
				int bx = 0;
				while(bx<6 && !blue[y][bx]) bx++;
				if(bx==6) blue[y][5] = true;
				else blue[y][bx-1] = true;				

				for(int j=2;j<6;j++)  //먼저 점수가 발생했는지 체크
					if(blue[0][j] && blue[1][j] && blue[2][j]&&blue[3][j]) {
						score++;
						blue = blueRemove(blue, j);
					}
				for(int j=0;j<4;j++) //연한색에 남은 블럭이 있는지 체크
					if(blue[j][1]) {
						blue = blueRemove(blue, 5);
						break;
					}
				
				//green
				int gy = 0;
				while(gy<6 && !green[gy][x] ) gy++;
				if(gy==6) green[5][x] = true;
				else green[gy-1][x] = true;
				
				for(int j=2;j<6;j++)  //먼저 점수가 발생했는지 체크
					if(green[j][0] &&green[j][1] &&green[j][2] &&green[j][3] ) {
						score++;
						green = greenRemove(green, j);
					}
				
				for(int j=0;j<4;j++)  //연한색에 남은 블럭이 있는지 체크
					if(green[1][j]) {
						green = greenRemove(green, 5);
						break;
					}

			}else if(t==2) { //1x2
				//blue
				int bx = 0;
				while(bx<6 && !blue[y][bx]) bx++;
				if(bx==6) {
					blue[y][5] = true;
					blue[y][4] = true;
				}
				else {
					blue[y][bx-1] = true;
					blue[y][bx-2] = true;
				}
				for(int j=2;j<6;j++)  //먼저 점수가 발생했는지 체크
					if(blue[0][j] && blue[1][j] && blue[2][j]&&blue[3][j]) {
						score++;
						blue = blueRemove(blue, j);
					}
				for(int c=0;c<2;c++)  //연한색에 남은 블럭이 있는지 체크 -> 파란색 보드에서 1x2블럭은 2행을 차지하므로 2번
					for(int j=0;j<4;j++)
						if(blue[j][1]) {
							blue=blueRemove(blue, 5);
							break;
						}
				
				//green
				int gy = 0;
				while(gy<6 && !green[gy][x] && !green[gy][x+1]) gy++;
				if(gy==6) {
					green[5][x] = true;
					green[5][x+1] =true;
				}else{
					green[gy-1][x] = true;
					green[gy-1][x+1] = true;
				}

				for(int j=2;j<6;j++)  //먼저 점수가 발생했는지 체크
					if(green[j][0] &&green[j][1] &&green[j][2] &&green[j][3] ) {
						score++;
						green = greenRemove(green, j);
					}
				for(int j=0;j<4;j++)  //연한색에 남은 블럭이 있는지 체크
					if(green[1][j]) {
						green = greenRemove(green, 5);
						break;
					}
			}else if(t==3) { //2x1
				//blue
				int bx = 0;
				while(bx<6 && !blue[y][bx] && !blue[y+1][bx]) bx++;
				if(bx==6) {
					blue[y][5] = true;
					blue[y+1][5] = true;
				}else {
					blue[y][bx-1] = true;
					blue[y+1][bx-1] = true;
				}
				for(int j=2;j<6;j++)   //먼저 점수가 발생했는지 체크
					if(blue[0][j] && blue[1][j] && blue[2][j]&&blue[3][j]) {
						score++;
						blue = blueRemove(blue, j);
					}
				for(int j=0;j<4;j++)  //연한색에 남은 블럭이 있는지 체크
					if(blue[j][1]) {
						blue=blueRemove(blue, 5);
						break;
					}
				//green
				int gy = 0;
				while(gy<6 && !green[gy][x] ) gy++;
				if(gy==6) {
					green[5][x] = true;
					green[4][x] = true;
				}
				else {
					green[gy-1][x] = true;
					green[gy-2][x] = true;
				}

				for(int j=2;j<6;j++)  //먼저 점수가 발생했는지 체크
					if(green[j][0] &&green[j][1] &&green[j][2] &&green[j][3] ) {
						score++;
						green = greenRemove(green, j);
					}
				
				for(int c=0;c<2;c++)  //연한색에 남은 블럭이 있는지 체크 -> 2x1 블럭은 초록색 보드에서 연한색 2행을 차지하므로 2번 실행
					for(int j=0;j<4;j++)
						if(green[1][j]) {
							green = greenRemove(green, 5);
							break;
						}
			}
		}
		int cnt = 0;
		for(int i=0;i<6;i++)
			for(int j=0;j<4;j++) {
				if(green[i][j]) cnt++;
				if(blue[j][i]) cnt++;
			}
		System.out.println(score);
		System.out.println(cnt);
	}
}