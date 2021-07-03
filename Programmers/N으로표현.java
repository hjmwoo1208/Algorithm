import java.util.*;
import java.io.*;

public class N으로표현 {
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
