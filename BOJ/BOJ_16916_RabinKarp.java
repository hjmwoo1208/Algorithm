import java.io.*;

/** 백준 - 부분문자열 (16916번)
 *  - 문자열 알고리즘
 *  - 라빈-카프 알고리즘
 */
public class BOJ_16916_RabinKarp {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text =  br.readLine();
        String pattern = br.readLine();

        int textLen = text.length(), ptnLen = pattern.length();
        if(ptnLen>textLen){ //패턴의 길이가 전체 문자열보다 더 길다면 탐색 불가
            System.out.println(0);
            return;
        }

        int NUM = 18, MOD = (int)1e9+7; //해시 기준 숫자, 모듈러 값
        long tHash = 0, pHash=0, pow = 1; // tHash = text의 해시값,  pHash = 패턴 문자열의 해시값 , pow = 최고차항 제곱값

        // 패턴의 해시값과 전체 문자열 초기 해시값 생성 -> 모듈러 연산을 통해서 해시값 오버플로우 방지.
        for(int i=0;i<ptnLen;i++){
            tHash = (tHash + text.charAt(ptnLen-1-i) * pow ) % MOD;
            pHash = (pHash+ pattern.charAt(ptnLen-1-i)*pow)%MOD;
            if(i!=ptnLen-1) pow = (pow*NUM)%MOD;
        }
        for(int i=0;i<=textLen-ptnLen;i++) {
            if(pHash==tHash){ //해시값이 같다면 일치하는 문자열이므로 탐색 종료
                System.out.println(1);
                return;
            }
            if(i==textLen-ptnLen) break; //마지막 탐색 구간에서는 더이상 탐색하지 않는다.
            long remove = (text.charAt(i)*pow)%MOD; //현재 문자열의 해시값에서 탐색이 종료된 문자의 해시값을 지운다.
            tHash =  NUM * (tHash-remove) + text.charAt(i+ptnLen); //전체 문자열 해시값 업데이트
            tHash %= MOD; //전체 해시값의 모듈러 연산
            tHash = tHash < 0? tHash+=MOD : tHash; //문자열의 해시값이 음수라면 양수로 변환해준다.
        }
        System.out.println(0);
    }
}