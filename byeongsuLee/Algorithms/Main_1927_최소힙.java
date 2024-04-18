package day0413;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_1927_최소힙 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();
		
		for(int i =0 ; i <N;i++) {
			int value = Integer.parseInt(br.readLine());
			
			//가장작은수 출력 및 제거 or 비어있으면 o출력
			if(value==0) {
				if(pq.isEmpty()) sb.append(0).append("\n");
				else {
					sb.append(pq.poll()).append("\n");
				}
			}else {
				pq.offer(value);
			}
		}
		System.out.println(sb.toString());
		
	}

}
