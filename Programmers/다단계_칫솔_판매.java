import java.util.*;
public class 다단계_칫솔_판매 {
    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {    
        Map<String,String> referMap = new HashMap<String, String>(); //추천인 map
        Map<String,Integer> sellMap = new LinkedHashMap<String,Integer>(); //판매원, 판매금액
        
        int totalSeller = enroll.length;
        for(int i=0;i<totalSeller;i++) {
        	referMap.put(enroll[i], referral[i]);
        	sellMap.put(enroll[i], 0);
        }
        
        int sellCnt = seller.length;
        for(int i=0;i<sellCnt;i++) {
        	String name = seller[i];
        	int my = amount[i] * 100;
        	while(true) {
        		int send = (int) (my * 0.1);
        		sellMap.put(name, sellMap.get(name)+my-send);
        		if(send==0) break;
        		String referName = referMap.get(name);
        		if("-".equals(referName)) break;
        		name = referName;
        		my = send;
        	}
        }

        int[] answer = new int[totalSeller];
        for(int i=0;i<totalSeller;i++) answer[i] = sellMap.get(enroll[i]);
        return answer;
    }
	public static void main(String[] args) {
		int re[] = solution(new String[] { "john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"}, new String[] {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"}
		, new String[] {"young", "john", "tod", "emily", "mary"}, new int[] {12, 4, 2, 5, 10});
		
//		int re2[] = solution(new String[] { "john", "mary", "edward"}, new String[] {"-", "john", "mary"}
//		, new String[] {"john", "edward"}, new int[] {12, 10});
		
		int 답 = 9;
		System.out.println(답);
	}
}