import java.util.ArrayList;
import java.util.List;

public class Rabin_Karp_Algorithm {
    /**
     *  [라빈 카프 알고리즘]
     *  - 일지하는 문자열을 찾는 알고리즘
     *  - 해쉬값을 비교하여 일치하는지를 찾는다.
     *  - 탐색은 뒤에서 부터 시작한다.
     *
     * @param str : 전체문자열
     * @param pattern : 비교할(패턴) 문자열
     * @return : 일치하는 문자열 시작 인덱스 모음
     */
    static List<Integer> RabinKarp(String str, String pattern){
        List<Integer> result = new ArrayList<>(); //일치하기 시작하는 인덱스의 집합

    	int X = 18; //
    	int MOD = (int)1e9 + 7;
        
        int strLength = str.length(); //전체 문자열의 길이
        int patternLength = pattern.length(); //패턴 문자열의 길이

        long sHash = 0; //전체 문자열의 해쉬값
        long pHash = 0; //패턴 해쉬값
        
        long p =  1;

        //패턴, 전체 문자열 해쉬값 구하기 -> 뒤에서부터 각 문자의 (아스키 코드 * 2^(length-1-index))
        for(int i=0;i<patternLength;i++){
        	pHash = (pHash + pattern.charAt(patternLength-1-i) * p)%MOD; //패턴의 해쉬값
        	sHash = (sHash + str.charAt(patternLength-1-i)* p)%MOD;  //전체 문자열 시작 해쉬값 : 0인덱스 ~ 패턴의 길이만큼의 해시값
        	if(i!=patternLength-1) p =  (p*X)%MOD;
        }
        for(int i=0;i<= strLength-patternLength;i++){
            if(pHash==sHash){
                result.add(i);
//                System.out.println(i + "번 자리");
            }
            if(i== strLength-patternLength) break;

            //전체 문자열의 해시값 업데이트
            long remove = str.charAt(i) *p;
            long add =  str.charAt(i+patternLength);

            sHash = (X*sHash) - (X*remove) + add;
            sHash %= MOD; 
            if(sHash < 0 ) sHash +=MOD; //!!!!!! 틀린 이유 : 음수값이 나왔을 때 양수변환
        }
        return result;
    }
    public static void main(String[] args) {
        List list = RabinKarp("ABEJlfjsdaoifnefmdnfoeif","fmdn");
        System.out.println(1<<0);

    }
}
