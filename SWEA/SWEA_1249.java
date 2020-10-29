import java.util.*;
import java.io.*;
public class SWEA_1249 {
	/* 우선순위 큐로 BFS를 한다.
	 * 이동한 루트의 복구 시간이 짧은 순으로 Queue에서 나온다.
	 * 이미 방문한 길은 복구 시간이 짧은 루트로 방문했기 때문에
	 * 다음 방문은 무시한다.
	 */
    static class Repair implements Comparable<Repair>{
        int r, c, sum;
        public Repair(int r, int c, int sum) {
            super();
            this.r = r;
            this.c = c;
            this.sum = sum;
        }
        @Override
        public int compareTo(Repair o) {
            return this.sum-o.sum;
        }
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int tc=1;tc<T+1;tc++) {
            int N = Integer.parseInt(br.readLine());
            char map[][] = new char[N][N];
            for(int i=0;i<N;i++) map[i] = br.readLine().toCharArray();
            boolean check[][] = new boolean[N][N];
            PriorityQueue<Repair> q = new PriorityQueue<Repair>();
            q.offer(new Repair(0, 0, map[0][0]-'0')); //좌상단에서 출발
            check[0][0] = true;
            int py[] = {-1,0,1,0}, px[]= {0,1,0,-1}; //상 우 하 좌
            while(!(q.isEmpty())) {
                Repair out = q.poll();
                if(out.r==N-1 && out.c==N-1) { //목적지에 도착
                    System.out.println("#"+tc+" "+out.sum); //제일 먼저 도착한 루트가 최소의 복구시간
                    break;
                }
                for(int p=0;p<4;p++) {
                    int y = out.r + py[p];
                    int x = out.c + px[p];
                    if(y<0 || x<0 ||y>N-1 ||x>N-1 || check[y][x]) continue; //방문한 곳은 이미 더 짧은 루트로 방문 한 곳이기에 탐색X
                    q.offer(new Repair(y, x, out.sum+ (map[y][x]-'0'))); //해당 루트의 복구 시간을 누적한다.
                    check[y][x] = true; //방문체크
                }
            }
        }
    }
}