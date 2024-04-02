package ex0401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_13913 {
    private static class Node{
        int idx;
        int count;
        public Node(int idx, int count){
            this.idx = idx;
            this.count = count;
        }
    }
    private static int n,k,result;
    private static boolean[] check = new boolean[100001];
    private static int[] answer = new int[100001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        bfs(n);
        System.out.println(result);
        for(int i = 0; i < 100001; i++){
            if(answer[i] != 0){
                System.out.print(answer[i] + " ");
            }
        }
    }//main end

    private static void bfs(int x){
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(x,0));
        check[x] = true;

        int idx = 0;
        while(!que.isEmpty()){
            Node now = que.poll();

            if(now.idx == k){
                result = now.count;
                return;
            }

            int next = now.idx * 2;
            if(next > 0 && next < 100001 && !check[next]){
                answer[idx] = next;
                que.offer(new Node(next, now.count+1));
                check[next] = true;
            }

            next = now.idx + 1;
            if(next > 0 && next < 100001 && !check[next]){
                answer[idx] = next;
                que.offer(new Node(next, now.count+1));
                check[next] = true;
            }

            next = now.idx - 1;
            if(next > 0 && next < 100001 && !check[next]){
                answer[idx] = next;
                que.offer(new Node(next, now.count+1));
                check[next] = true;
            }
            idx++;
        }
    }//bfs end
}//class end
