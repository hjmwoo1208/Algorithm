/* 프로그래머스 - 위클리챌린지 7주차
 * */
public class 입실_퇴실 {
    public static int[] solution(int[] enter, int[] leave) {
        int people = enter.length;
        int[] answer = new int[people];
        boolean check[][] = new boolean[people+1][people+1]; // 각 번호의 사람이 확실하게 만난 사람들 체크
        boolean in[] = new boolean[people+1]; //현재까지 입실한 사람들의 t/f
        int pointer = 0;
        in[enter[pointer++]] = true;
        for(int p=0;p<people;p++) {
        	if(!in[leave[p]])
        		while(pointer<people && !in[leave[p]]) in[enter[pointer++]] = true;
        	for(int i=1;i<people+1;i++)
        		if(in[i])
        			for(int j=1;j<people+1;j++)
        				if(i!=j && in[j]) check[i][j] = true;
        	in[leave[p]] = false;
        }
        for(int i=1;i<=people;i++)
        	for(int j=1;j<=people;j++)
        		if(check[i][j]) answer[i-1]++;
        return answer;
    }
	public static void main(String[] args) {
		int res[] = solution(new int[] {1,3,2}, new int[] {1,2,3});
	}
}
