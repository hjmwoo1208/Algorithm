import java.util.*;
/**
 * @author WOOHJ
 * 
 * 백준 1990 - 소수인팰린드롬
 * 
 * [ 시간초과 방지 방법]
 * 1. "에라토스테네스의 체"를 사용하여 배수의 숫자들을 걸러준다.
 * 2. 약수를 구하고자 하는 숫자의 "제곱근"까지만 탐색한다.
 * 3. 대칭수의 조건을 사용한다. 
 * 		짝수 자릿수를 갖는 모든 대칭수는 반드시 11을 약수로 갖고 있다. 
 * 		고로 짝수의 자릿수를 갖는 회문 소수는 11이 유일하다.
 * 		https://namu.wiki/w/%EB%8C%80%EC%B9%AD%EC%88%98
 * 
 */

public class BOJ_1990 {
	/**
	 * 팰린드롬 수인지 판별하는 메소드
	 * @param Num 
	 * @return false = 팰린드롬 수X, true = 팰린드롬 수 O
	 */
	static boolean check(int Num) {
		StringBuffer sb = new StringBuffer(String.valueOf(Num));
		if(sb.length()==1) return true;
		int len = sb.length();
		int len2 = sb.length()/2;
		int startIndex = len2 + (len%2);
		String start = sb.substring(0, len2);
		StringBuffer end = new StringBuffer(sb.substring(startIndex, len));
		end.reverse();
		if(Integer.parseInt(start) == Integer.parseInt(end.toString())) return true;
		return false;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		
//		방법 1 + 방법 2 사용
		boolean visited[] = new boolean[b+1];
		int len = (int)Math.sqrt(b); // b의 제곱근까지 탐색한다.
		for(int i=2;i<=len;i++	) {
			if(visited[i]) continue;
			for(int j=i*i;j<=b;j+=i) visited[j] = true;
		}
		for(int num=a;num<=b;num++)
			if(!visited[num] && check(num)) System.out.println(num);
		System.out.println(-1);
		
//		방법 2+ 방법 3 사용		
//		if(b>10000000) b=10000000;
//		for(int num=a;num<=b;num++)
//			if(check(num)) {
//				boolean flag = false;
//				int len = (int)Math.sqrt(num);
//				for(int i=2;i<=len;i++)
//					if(num%i==0) {
//						flag = true;
//						break;
//					}
//				if(!flag) System.out.println(num);		
//			}
//		System.out.println(-1);
	}
}