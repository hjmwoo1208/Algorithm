import java.util.*;
/*
 * 백준 알고리즘 : 최단거리
 * 
 * 노드 클래스를 만들어야됨 --> 20000*20000 int 배열만들면 메모리 초과 --> 노트 클래스를 Array에 넣어서 선언하는 방법
 * 
 * Compareable 인터페이스 상속받아서 구현하는 방법
 * 
 * 맞았음
 * http://zoonvivor.tistory.com/99 참고
 * 
 * http://www.gisdeveloper.co.kr/?p=4452
 */
public class BJ_1753_3 {
	//public static int dist[] = new int[20002];
	//출처: http://minemi.tistory.com/39 [미네미블로그]
	
	public static class Edge
	{
		int end;
		int value;
		
		Edge(int end, int value)
		{
			this.end = end;
			this.value = value;
		}
	}
	
	public static class distance implements Comparable<distance>
	{

		int index;
		int dist;	
		
		distance(int index, int dist)
		{
			this.index = index;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(distance d) {
			//if (this.getF() > target.getF()) {
			if(this.dist>d.dist) 
			{
	            return 1;
	        } 
			else if (this.dist  < d.dist) 
			{
	            return -1;
	        }
	 
	        return 0;
		}
		/*
		 * 현재 객체가 비교대상보다 작으면 --> 음수
		 * 현재 객체가 비교대상이랑 같으면 --> 0
		 * 현재 객체가 비교대상보다 크면    --> 양수
		 */
	}
	
	public static void main(String[] args) {
		int V,E,K;
		int d[][];
		int uvw[][];
		boolean distB[];
		int dist[];
		Scanner sc = new Scanner(System.in);
		
		StringTokenizer stz;
		stz = new StringTokenizer(sc.nextLine());
		V = Integer.parseInt(stz.nextToken());
		E = Integer.parseInt(stz.nextToken());
		K = Integer.parseInt(sc.nextLine());
		
		dist = new int[V+2];
		distB = new boolean[V+2];
		
		Arrays.fill(distB, true);
		Arrays.fill(dist, Integer.MAX_VALUE);

		ArrayList<Edge>[] dList = new ArrayList[V + 2];
		for(int i=0;i<=V;i++)
			dList[i] = new ArrayList<Edge>();
		
		int u,v,w;
		boolean flag = true;
		for(int i =0;i<E;i++)
		{
			stz = new StringTokenizer(sc.nextLine());
			dList[Integer.parseInt(stz.nextToken())].add(new Edge(Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken())));
		}
		
		dist[K] = 0;
		
		distance tempdist;
		tempdist = new distance(K,dist[K]);
		
		PriorityQueue<distance> q = new PriorityQueue<distance>();
		q.offer(tempdist);
		
		
		int temp;
		distance tempdist2;
		while(!(q.isEmpty()))
		{
			tempdist = q.poll();
			
			if(distB[tempdist.index] == true)
			{
				distB[tempdist.index] = false;
				for(int i = 0;i<dList[tempdist.index].size();i++)
				{
					temp = dist[tempdist.index] + dList[tempdist.index].get(i).value;
					if(temp<dist[dList[tempdist.index].get(i).end])
					{
						dist[dList[tempdist.index].get(i).end] = temp;
						tempdist2 = new distance(dList[tempdist.index].get(i).end, temp);
						q.offer(tempdist2);
					}
					
				}
			}
			
		}//while
		
		for(int i  = 1; i<=V; i++)
		{
			if(distB[i] == true)
				System.out.println("INF");
			else
				System.out.println(dist[i]);
		}
	}//main
	
	
	
}
