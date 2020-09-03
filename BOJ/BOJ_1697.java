import java.util.*;
public class BOJ_1697 { 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); //수빈의 현재 위치
		int K = sc.nextInt(); //동생의 위치
		boolean check[] = new boolean[100001]; //현재 위치는 다시 방문하지 않는다. --> 최초 방문이 최소 방문 시간
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(N); //수빈의 현재 위치부터 탐색
		check[N] = true; //현재 위치 방문 체크
		int cnt = 0; //시간 체크
		while(!(q.isEmpty())) {
			int size = q.size(); //동시간대 가능한 위치 탐색
			for(int s=0;s<size;s++) {
				int out = q.poll();
				if(out == K) { //찾았다.
					System.out.println(cnt);
					return;
				}
				if(out-1>=0 && check[out-1]==false) { //한칸 뒤로
					q.offer(out-1);
					check[out-1] = true;
				}
				if(out>K) continue; //동생의 위치가 현재 수빈의 위치보다 뒤에 있으면 앞을 탐색할 필요 X
				if(out+1 <=100000 && check[out+1] == false) { //한칸 앞으로
					q.offer(out+1);
					check[out+1] = true;
				}
				if(out*2 <=100000 && check[out*2]==false) { //순간이동
					q.offer(out*2);
					check[out*2] = true;
				}
			}
			cnt++;
		}
	}
}