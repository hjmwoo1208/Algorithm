import java.util.*;
import java.io.*;

public class N으로표현 {
	// 문제가 개편되었습니다. 이로 인해 함수 구성이나 테스트케이스가 변경되어, 과거의 코드는 동작하지 않을 수 있습니다.
	// 새로운 함수 구성을 적용하려면 [코드 초기화] 버튼을 누르세요. 단, [코드 초기화] 버튼을 누르면 작성 중인 코드는 사라집니다.
	static int answer = 10;
	public static int solution(int N, int number) {
        answer = 10;
        if(N == number) return 1;
        DFS(N, number, 0,0);
        return answer<= 8? answer: -1;
    }
    
    static void DFS(int N, int number, int cnt, int preNum) {
    	if(cnt>8) return;
    	if(preNum == number) 
    		answer = Math.min(answer, cnt);
    	int tmp = N;
    	for(int i=1;i<=8-cnt;i++	) {
    		DFS(N,number, cnt+i, preNum+tmp);
    		DFS(N, number, cnt+i, preNum-tmp);
    		DFS(N,number,cnt+i, preNum/tmp);
    		DFS(N,number,cnt+i, preNum*tmp);
    		
    		tmp = tmp*10 + N;
    	}
    }
    
	public static void main(String[] args) {
		System.out.println(solution(5,12));
		System.out.println(solution(2, 11));
	}

}
