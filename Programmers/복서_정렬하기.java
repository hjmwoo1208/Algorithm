import java.util.*;
public class 복서_정렬하기 {
	static class Player implements Comparable<Player>{
		int num, weight, overWin;
		double winRate;
		public Player(int num, int weight, int overWin, double winRate) {
			super();
			this.num = num;
			this.weight = weight;
			this.overWin = overWin;
			this.winRate = winRate;
		}
		@Override
		public int compareTo(Player p) {
			if(this.winRate>p.winRate) return -1;
			else if(this.winRate==p.winRate) {
				if(this.overWin>p.overWin) return -1;
				else if(this.overWin==p.overWin) {
					if(this.weight>p.weight) return -1;
					else if(this.weight==p.weight) {
						if(p.num> this.num) return -1;
						return 1;
					}return 1;
				}else return 1;
			}
			return 1;
		}
	}
    public static int[] solution(int[] weights, String[] head2head) {
        int total = weights.length;
        List<Player> list = new ArrayList<Player>();
        for(int i=0;i<total;i++) {
        	int playCnt = 0, winCnt = 0, overWinCnt = 0;
        	String score = head2head[i];
        	for(int j=0;j<total;j++) {
        		if(i==j || score.charAt(j)=='N') continue;
        		playCnt++;
        		if(score.charAt(j) == 'W') {
        			winCnt++;
        			if(weights[j] > weights[i]) overWinCnt++;
        		}
        	}
        	double cal = 0;
        	if(playCnt>0) cal = winCnt/ (double) playCnt;
        	list.add(new Player(i+1,weights[i],overWinCnt,cal));
        }
        Collections.sort(list);
        int [] answer = new int[total];
        for(int i=0;i<total;i++) answer[i] = list.get(i).num;
        return answer;
    }
	public static void main(String[] args) {
		int res[] = solution(new int[] {60,70,60},new String[] {"NNN","NNN","NNN"});
		System.out.println();
	}
}
