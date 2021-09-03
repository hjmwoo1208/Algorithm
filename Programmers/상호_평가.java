import java.util.*;
public class 상호_평가 {
	static class Score implements Comparable<Score>{
		int num, point;
		public Score(int num, int point) {
			super();
			this.num = num;
			this.point = point;
		}
		@Override
		public int compareTo(Score o) {
			return this.point - o.point;
		}
	}
    public static String solution(int[][] scores) {
        StringBuilder answer = new StringBuilder("");
        int students = scores.length;
        for(int i=0;i<students;i++) {
        	List<Score> list = new ArrayList<Score>();
        	int sum = 0;
        	for(int j=0;j<students;j++) {
        		sum += scores[j][i];
        		list.add(new Score(j, scores[j][i]));
        	}
        	Collections.sort(list);
        	boolean check = false;
        	if(list.get(0).point == scores[i][i]) {
        		if(list.get(1).point != scores[i][i]) {
        			sum -= scores[i][i];
        			check = true;
        		}
        	}else if(list.get(students-1).point==scores[i][i]){
        		if(!check)
        			if(list.get(students-2).point != scores[i][i]) {
        				sum -= scores[i][i];
        				check = true;
        			}
        	}
        	if(!check) answer.append(ABC(sum/students));
        	else answer.append(ABC(sum/(students-1)));
        }
        return answer.toString();
    }
    static char ABC(int sum) {
    	if(sum>=90) return 'A';
    	if(sum>=80) return 'B';
    	if(sum>=70) return 'C';
    	if(sum>=50) return 'D';
    	return 'F';
    }

	public static void main(String[] args) {
		System.out.println(solution(new int[][] {
			{100,90,98,88,65},{50,45,99,85,77},{47,88,95,80,67},{61,57,100,80,65},{24,90,94,75,65}
		}));
		System.out.println(solution(new int[][] {
			{70,49,90},{68,50,38},{73,31,100}
		}));
		System.out.println(solution(new int[][] {
			{50,90},{50,87}
		}));

	}

}
