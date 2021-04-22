import java.util.*;
import java.io.*;
/* 컨베이어 벨트 위의 로봇 - 성공
 * <주의>
 * 내려가는 곳일 때의 if를 주의 */
public class BOJ_20055 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stz.nextToken()), N2 = N*2;
		int K = Integer.parseInt(stz.nextToken()), turn = 0;
		int belt[] = new int[N2];
		stz = new StringTokenizer(br.readLine());
		for(int i=0;i<N2;i++) belt[i] = Integer.parseInt(stz.nextToken());
		Queue<Integer> robot = new LinkedList<Integer>();
		boolean check[] = new boolean[N2];
		int up = 0, down= N-1,zero=0;
		while(zero<K) {
			up = (up+(N2-1))%N2; //올라가는 위치 옮기기
			down = (down+(N2-1))%N2; //내려가는 위치 옮기기
			int size = robot.size(); //같은 시간대에 올라간 로봇들
			for(int s=0;s<size;s++) {
				int out = robot.poll();
				check[out] = false; 
				if(out==down) continue; //로봇이 있는 곳이 내려가는 위치면 내려간다.
				int next = (out+1)%N2; //로봇의 다음 위치
				if(!check[next] && belt[next]>0) { //이동이 가능하다면
					belt[next]-= 1; //이동하고 내구도 낮춤
					if(belt[next]==0) zero++; 
					if(next != down) { //내려가는 곳이 아니라면 이동한다. -> 내려가는 곳이면 queue에서 빠진다.
						check[next] = true;
						robot.offer(next);
					}
				}else { //이동이 불가능하면 현재 자리에 그대로 있는다.
					robot.offer(out);
					check[out]= true;
				}
			}
			if(!check[up] && belt[up]>0) { //올라기는 곳이 비었으면서 내구도가 0이 아닐 경우 올라가는 곳에 로봇을 채운다.
				check[up] = true;
				robot.offer(up);
				belt[up] -= 1;
				if(belt[up]==0) zero++;
			}
			turn++;
			if(zero>=K) break;
		}
		System.out.println(turn);
	}
}