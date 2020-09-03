import java.util.*;
public class BJ_2961 {
	static int min = 1000000000,N, set[]= new int[10], sb[][]=new int[10][2]; //set : 조합 저장 , sb : 신맛 쓴맛 저장
	static void go(int size, int index, int before) { //size : 선택할 재료의 갯수, index : 현재까지 선택한 재료의 갯수, before : 이전 재귀에서 들어온 현재 시작 가능한 인덱스 번호
		if(size == index) {
			int s = 1; //신맛의 곱을 위한 초기값
			int b = 0; //쓴맛의 합을 위한 초기값
			for(int i=0;i<size;i++){ //선택한 재료의 신맛, 쓴맛 계산
				s *=sb[set[i]][0]; 
				b += sb[set[i]][1];
			}
			min = Math.min(min, Math.abs(s-b)); //비교
			return;
		}
		int limit = N-size+index;
		for(int i=before;i<=limit;i++) { //조합
			set[index] = i; //선택한 재료 번호 저장
			go(size,index+1,i+1);
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		for(int i=0;i<N;i++) {
			sb[i][0] = sc.nextInt();
			sb[i][1]	= sc.nextInt();
		}
		for(int i=1;i<=N;i++) go(i,0,0); //재료 갯수별로 조합
		System.out.println(min);
	}
}