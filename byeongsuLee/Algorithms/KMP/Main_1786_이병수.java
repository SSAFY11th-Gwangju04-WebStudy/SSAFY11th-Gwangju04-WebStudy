package day0329;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1786_이병수 {

	public static void main(String[] args)throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String T = br.readLine();
		String P = br.readLine();
		StringBuilder sb= new StringBuilder();
		int [] p  = new int[P.length()];
		int [] t  = new int[T.length()];
		int count =0;
		//0 KMP 알고리즘을 사용하는이유 : 문자열의 포함여부를 확인 -> 틀렸다면 처음부터 보는 게 아니라 
		
		// 1. 테이블 만들기
		// i : p의 i까지의 부분문자열   ,  p=abc 일떄 i=0,1,2 -> a ab abc 를 나타냄
		// j : p 의 포인터
		// p[] : 접두사 접미사가 같은 개수
		// 접미사와 접두사가 같은지? 확인
		// i=1부터 j=0과 비교 후 같으면 j+1   틀리면 j = p[j-1]
		// j = 현재 문자를 제외한 이전 접두사와 접미사가 일치하는 최대 길이다.
		int j = 0;
		for(int i= 1 ; i < P.length();i++) {
			//0은 안함

			//i ,j 가 같지않으면 이전 접미사 접두사 같은  최대 길이
			while(j>0 && P.charAt(i)!=P.charAt(j)) {
				j=p[j-1];
			}
			
			//같으면 i와 j를 올려준다.
			if(P.charAt(i)==P.charAt(j)) {
				p[i] = ++j;
			}
			//j ==0이고 같은값이 없을때는 p[i] = 0 인데 기본값이 0이라 아무것도안해줘도됨
		}
		//2. 문자열 포함 확인
		 j = 0 ;
		for(int i = 0 ; i <T.length(); i++) {
		
			while(j>0 && T.charAt(i)!=P.charAt(j)) {
				j=p[j-1];
			}
			if(T.charAt(i)==P.charAt(j)) {
				++j;
				if(j==p.length) {
					count++;
					//i-j+1= 해당 인덱스  +1해야 번쨰가나옴
					sb.append(i-j+2).append(" ");
					
					//방법 1: 시작 위치에서 한칸 오른쪽부터 다시 시작 i = i-j+1 
					//시간초과남 - 2중포문이랑다를바없다. 
					//방법 2 : p[j-1]  본문 : aaaaa , p : aaa  첫번쨰 비교후 i 값을 1로 이동 x and i=i+1 그대로 둠
					

					j=p[j-1];
				}
			}
		}
		System.out.println(count);
		System.out.println(sb.toString());
		
		

		
	}

}
