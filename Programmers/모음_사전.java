import java.util.*;
public class 모음_사전 {
    public static int solution(String word) {
        int answer = 0;
        char alpa[] = {'A', 'E', 'I', 'O', 'U'};
        
        Stack<StringBuffer> stk = new Stack<StringBuffer>();
        for(int i=4;i>-1;i--) stk.add(new StringBuffer("").append(alpa[i]));
        
        while(!stk.isEmpty()) {
        	StringBuffer out = stk.pop();
        	answer++;
        	if(word.length() == out.length() && word.equals(out.toString())) break;
        	if(out.length()==5) continue;
        	for(int i=4;i>-1;i--) stk.add(new StringBuffer(out).append(alpa[i]));
        }
        return answer;
    }
	public static void main(String[] args) {
		System.out.println(solution("AAAAE"));
		StringTokenizer stz = new StringTokenizer("dfnasldfnldsakf");
	}
}

/*
U
O
I
E
	A -> pop : 1
AU
AO
AI
AE
	AA -> pop : 2
AAE
AAI
AAO
AAU
	AAA -> pop :3
AAAU
AAAO
AAAI
	AAAE -> pop : 10
	AAAA -> pop : 4
	AAAAU -> 9
	AAAAO -> 8
	AAAAI -> 7
	AAAAE -> pop : 6
	AAAAA -> pop : 5
AAAEU
AAAEO
AAAEI
AAAEE
	AAAEA : pop : 11

*/