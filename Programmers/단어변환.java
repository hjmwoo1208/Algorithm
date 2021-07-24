public class 단어변환 {
    public static int solution(String begin, String target, String[] words) {
    	MIN = 10000;
        dfs(begin, target, 0, new boolean[words.length], words);
        return MIN==10000?0:MIN;
    }
    static int MIN = 10000;
    static void dfs(String current, String target, int count, boolean[] visited, String[] words) {
    	if(current.equals(target)) {
    		MIN = Math.min(count, MIN);
    		return;
    	}
    	int len = words.length;
    	for(int i=0;i<len;i++) {
    		if(visited[i]) continue;
    		int strLen = current.length(), cnt = 0;
    		for(int s=0;s<strLen;s++) {
    			if(words[i].charAt(s) != current.charAt(s)) cnt++;
    			if(cnt>1) break;
    		}
    		if(cnt==1) {
    			visited[i] = true;
    			dfs(words[i], target, count+1, visited, words);
    			visited[i] = false;
    		}
    	}
    }
    
	public static void main(String[] args) {
		System.out.println(solution("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log", "cog"}));
		System.out.println(solution("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log"}));
	}
}
