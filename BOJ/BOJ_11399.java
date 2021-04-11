import java.util.*;
/* ATM - 성공 */
public class BOJ_11399 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		int time[] = new int[N];
		for(int i=0;i<N;i++) time[i] = sc.nextInt();
		Arrays.sort(time);
		long min = time[0];
		long sum = time[0];
		for(int i=1;i<N;i++) {
			sum += time[i];
			min += sum;
		}
		System.out.println(min);
	}
}