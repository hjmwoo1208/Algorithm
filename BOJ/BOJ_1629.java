import java.util.*;
/* 곱셈 : 분할 정복을 이용한 거듭제곱*/
public class BOJ_1629 {
	static long ABC(int A, int B, int C) {
		if(B == 1) return A % C; // A^1 일 때
		long B2 = ABC(A,B/2,C) % C; // A^(B/2)값을 구해서 
		long BB = (B2*B2)%C; // 제곱한다.
		if(B%2>0) BB = (BB*A) %C; //B가 만약 홀수라면 A를 한번 더 곱해준다.
		return BB;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer stz = new StringTokenizer(sc.nextLine());
		System.out.println(ABC(Integer.parseInt(stz.nextToken()),Integer.parseInt(stz.nextToken()),Integer.parseInt(stz.nextToken())));
	}
}