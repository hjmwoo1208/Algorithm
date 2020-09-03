import java.util.*;
public class BOJ_1697 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		boolean check[] = new boolean[100001];
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(N);
		check[N] = true;
		int cnt = 0;
		while(!(q.isEmpty())) {
			int size = q.size();
			for(int s=0;s<size;s++) {
				int out = q.poll();
				if(out == K) { //찾았다.
					System.out.println(cnt);
					return;
				}
				if(out-1>=0 && check[out-1]==false) {
					q.offer(out-1);
					check[out-1] = true;
				}
				if(out>K) continue;
				if(out+1 <=100000 && check[out+1] == false) {
					q.offer(out+1);
					check[out+1] = true;
				}
				if(out*2 <=100000 && check[out*2]==false) {
					q.offer(out*2);
					check[out*2] = true;
				}
			}
			cnt++;
		}
	}
}