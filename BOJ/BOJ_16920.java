import java.util.*;
import java.io.*;
/* 확장 게임 - 성공
 *  !!! 시간초과 주의 !!!
 *  !!! 확장시 방향이 바뀔 수 있음 주의 !!!!
 *  !!! player는 1부터 시작이므로 배열 생성시 +1 필수 !!!
 *  <풀이 방법>
 *  1. 각각의 플레이어의 위치를 Queue에 담는다.
 *  2. BFS를 한다. 
 *  	2-1. 확장 도중 방향이 바뀔 수 있으므로 한칸씩 확장해서 Si만큼 for문을 돌린다.
 *  	2-2. 현재 player의 Queue가 비었다면 확장을 멈춘다 *** (시간초과를 방지하기 위한 중요한 조건)
 *  	2-3. 어떠한 player에서 한칸의 확장이라도 진행되었다면 flag가 true로 바뀌며 while이 계속 진행된다.
 *  	2-4. 확장되고 있는 칸을 세어준다. (시간초과 방지용)
 * */
public class BOJ_16920 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());
		int playerCnt = Integer.parseInt(stz.nextToken());
		
		int S[] = new int[playerCnt+1]; //각 플레이어의 Si  -> player는 1부터 시작하므로 +1해서 선언한다**
		stz = new StringTokenizer(br.readLine());
		for(int i=1;i<playerCnt+1;i++) S[i] = Integer.parseInt(stz.nextToken());
		
		Queue<int[]>[] qList = new LinkedList[playerCnt+1];
		for(int i=0;i<playerCnt+1;i++) qList[i] = new LinkedList<int[]>();
		int cnt[] = new int[playerCnt+1];
		int map[][] = new int[N][M];
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++) {
				if(str.charAt(j)=='#') map[i][j] = 10;
				if(str.charAt(j)>='1' && str.charAt(j) <='9') {
					int num = str.charAt(j) -'0';
					qList[num].offer(new int[] {i,j}); //각 플레이어의 Queue에 위치를 담는다.
					map[i][j] = num;
					cnt[num]++;
				}
			}
		}
		
		int py[] = {-1,0,1,0}, px[] = {0,1,0,-1};
		boolean flag = true; //기저조건 : 확장 유무를 판단. flag가 false이면 더이상 확장이 불가
		while(flag) {
			flag = false;
			for(int player=1;player<playerCnt+1;player++) {
				if(qList[player].isEmpty()) continue;
				for(int len=0;len<S[player];len++) { // 성을 확장을 할 때 방향이 바뀔 수 있다!!! ** 
					Queue<int[]> q = qList[player];
					if(q.isEmpty()) break; // !!!!! 시간초과를 방지하기 위한 기저조건 !!!! -> Si의 값이 10^9이기 때문에 q가 비었으면 for문 수행하지 않도록 막는다.
					int size = q.size();
					for(int s=0;s<size;s++) {
						int out[] = qList[player].poll();
						for(int p=0;p<4;p++) {
							int y = out[0] + py[p];
							int x = out[1] + px[p];
							if(y<0 || x<0 ||y>=N||x>=M) continue;
							if(map[y][x] ==0) {
								map[y][x] = player;
								q.offer(new int[] {y,x});
								flag =true;
								cnt[player]++;
							}
						}
					}
				}	
			}
		}//while
		for(int i=1;i<playerCnt+1;i++) System.out.print(cnt[i]+" ");
	}
}