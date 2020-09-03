import java.util.*;
import java.io.*;
/* <주의>
 * - 여섯 알 이상이 연속적으로 놓인 경우에는 이긴 것이 아니다.
 * - 연속된 다섯 개의 바둑알 중에서 가장 왼쪽에 있는 바둑알의 위치를 출력
 */
public class Jungol_1733 { 
	static int omok[][] = new int[20][20];
	static int[] py = {-1,-1,-1,0,1,1,1,0}; //대각선 가능
	static int[] px = {-1,0,1,1,1,0,-1,-1};
	static int[] play() {
		boolean check[][][] = new boolean[20][20][8]; //8방향 체크
		for(int i=1;i<=19;i++)
			for(int j=1;j<=19;j++)
				if(omok[i][j]>0) {
					Queue<int[]> q = new LinkedList<int[]>();
					for(int p=0;p<8;p++) { //현재 위치에서 8방향으로 진출 가능성 탐색
						int y = i+py[p];
						int x = j+px[p];
						if(y<1 ||x<1||y>19||x>19) continue;
						if(omok[y][x] == omok[i][j] && check[i][j][p]==false && check[y][x][p] ==false) {
							q.offer(new int[] {i,j,1,p}); // r,c,횟수,방향
							check[i][j][p] = true;//현재 위치에서 진출 가능한 방향으로 Queue에 넣어줌
						}
					}
					while (!(q.isEmpty())) {
						int[] out = q.poll();
						int y = out[0] + py[out[3]];
						int x = out[1] + px[out[3]];
						if (y >= 1 && x >= 1 && y <= 19 && x <= 19) // or로 가지치면 경계에서 5개일 때 체크하는 if문에 들어가지 않음
							if (omok[y][x] == omok[i][j] && check[y][x][out[3]] == false) { // 다음 방향으로 진출 가능하다면
								check[y][x][out[3]] = true;
								q.offer(new int[] { y, x, out[2] + 1, out[3] });
								continue; //6이상을 체크하기 위해서 continue로 flag
							}
						if (out[2] == 5) { 
							if ((j > out[1]) || (j == out[1] && i > out[0])) //도착지가 더 우선순위가 높을 때
								return new int[] { omok[i][j], out[0], out[1] }; //시작점위치를 바꿔서 return
							return new int[] { omok[i][j], i, j };
						}
					}//while
				}//if
		return new int[] {0}; //승부를 알 수 없을 때.
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=1;i<=19;i++) {
			StringTokenizer stz = new StringTokenizer(br.readLine());
			for(int j=1;j<=19;j++) omok[i][j] = Integer.parseInt(stz.nextToken());
		}
		int[] res = play();
		if(res.length == 1) System.out.println(0);
		else 	System.out.println(res[0]+"\n"+res[1]+" "+res[2]);
	}//main
}