import java.util.*;
public class 직업군_추천하기 {
	static class Job{
		String Job_name;
		Map<String,Integer> score = new HashMap<String, Integer>();
		public Job(String job_name, String s5, String s4,String s3, String s2,String s1) {
			super();
			Job_name = job_name;
			this.score.put(s5, 5);
			this.score.put(s4, 4);
			this.score.put(s3, 3);
			this.score.put(s2, 2);
			this.score.put(s1, 1);
		}
		
		int sumScore(String[] languages, int[] preference) {
			int size = languages.length;
			int sum = 0;
			for(int i=0;i<size;i++) {
				Integer lscore = score.get(languages[i]);
				if(lscore==null) continue;
				sum += (lscore*preference[i]);
			}
			return sum;
		}
	}
    public static String solution(String[] table, String[] languages, int[] preference) {
        String answer = null;
        int total_score = 0;
        
        List<Job> job_list = new ArrayList<Job>();
        for(String str : table) {
        	StringTokenizer stz = new StringTokenizer(str);
        	job_list.add(new Job(stz.nextToken(), stz.nextToken(), stz.nextToken(), stz.nextToken(), stz.nextToken(), stz.nextToken()));
        }
        
        int length = table.length;
        for(int i=0;i<length;i++) {
        	int sum = job_list.get(i).sumScore(languages, preference);
        	if(answer==null) {
        		answer = job_list.get(i).Job_name;
        		total_score = sum;
        	}else {
        		if(total_score==sum) {
        			if(job_list.get(i).Job_name.compareTo(answer)<0) answer= job_list.get(i).Job_name;
        		}else if(total_score<sum) {
        			total_score = sum;
        			answer = job_list.get(i).Job_name;
        		}
        	}
        }
        return answer;
    }
	
	
	public static void main(String[] args) {
		
		System.out.println(solution(new String[] {"SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"}
		, new String[] {"PYTHON", "C++", "SQL"}, new int[] {7, 5, 5}));
		System.out.println(solution(new String[] {"SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"},
				new String[] {"JAVA", "JAVASCRIPT"}, new int[] {7, 5}));
	}
}
