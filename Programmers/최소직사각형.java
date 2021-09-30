/* 위클리 챌린지 - 8주차 */
public class 최소직사각형 {
	public int solution(int[][] sizes) {
       int MAX =0 , MIN = 0;
       int len = sizes.length;
       for(int i=0;i<len;i++) {
    	   int ga, se; //ga = max
    	   if(sizes[i][0]<sizes[i][1]) {
    		   ga = sizes[i][1];
    		   se = sizes[i][0];
    	   }else {
    		   ga = sizes[i][0];
    		   se = sizes[i][1];
    	   }
    	   if(MAX<ga) MAX = ga;
    	   if(MIN <se) MIN = se;
       }
        return MAX * MIN;
    }
	public static void main(String[] args) {
	}
}