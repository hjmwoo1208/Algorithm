import java.util.*;
public class SWEA_5515 {
	public static void main(String[] args) {
		int month[] = {31,29,31,30,31,30,31,31,30,31,30,31}; //각 월의 일수
		int monthDayOne[] = new int[12]; //각 월의 1일의 요일
		monthDayOne[0] = 4; //2016년 1월 1일 = 금요일
		for(int i=1;i<12;i++) monthDayOne[i] = ((month[i-1]%7)+monthDayOne[i-1])%7; //각 달의 1일의 요일을 구한다.
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=1;tc<T+1;tc++) {
			int m = sc.nextInt();
			int d = sc.nextInt();
			//d일과 1일의 차이만큼 모듈값을 요일에 더하면 된다. 6->0으로 가기 위해 %7을 해줌
			System.out.println("#"+tc+" "+(monthDayOne[m-1]+((d-1)%7))%7); 
		}
	}
}