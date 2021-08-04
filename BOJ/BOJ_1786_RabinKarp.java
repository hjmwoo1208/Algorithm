import java.io.*;
/**
 * 
 * @author WOOHJ
 * 라빈- 카프 알고리즘
 * 문자열 알고리즘
 * 
 */

public class BOJ_1786_RabinKarp {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String pattern = br.readLine();
		int cnt = 0; //일치하는 갯수 카운트
		StringBuffer sb = new StringBuffer(""); // 일치하는 위치 문자열로 저장 
        int strLength = str.length(), patternLength = pattern.length(); 
        if(patternLength>strLength) System.out.println(0); //패턴이 문자열보다 크다면 패스
	    else {
	    	int X = 18 , MOD = (int)1e9 + 7; //해시 계산 숫자는 18 , MOD : 모듈러연산
	        long sHash = 0, pHash = 0, p =  1; // sHash : 전체 문자열에 대한 해쉬값 ,  pHash : 패턴 해시값, p : 첫번째 자리에 곱할 제곱승
	        for(int i=0;i<patternLength;i++){
	            pHash = (pHash + pattern.charAt(patternLength-1-i) * p)%MOD; 
	            sHash = (sHash + str.charAt(patternLength-1-i)* p)%MOD; 
	        	if(i!=patternLength-1) p =  (p*X)%MOD;
	        }
	        for(int i=0;i<= strLength-patternLength;i++){
	            if(pHash==sHash) { //일치하는 문자열이라면
	            	cnt++;
	            	sb.append((i+1)+" "); 
	            }
	            if(i== strLength-patternLength) break;	 //탐색 가능한 범위 중 마지막이면 더이상 해시값 업데이트 진행 NO
	            long remove = str.charAt(i) *p; //비교 문자열을 옮기면서 삭제될 문자의 해시값
	            long add =  str.charAt(i+patternLength);	//추가될 문자의 해시값
	            sHash = (X*sHash) - (X*remove) + add; //지워질 문자의 해시값을 빼주고 추가될 문자의 해시값을 더해준다.
	            sHash %= MOD; //범위를 벗어날 수 있으므로 모듈러 연산 진행 
	            if(sHash < 0 ) sHash +=MOD; //모듈러 연산 후 음수가 나오면 모듈러 값을 더해준다.
	        }
	        System.out.println(cnt); //총 일치 갯수
	        System.out.println(sb); //일치하는 위치의 인덱시
        }
	}
}