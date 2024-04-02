package ex0401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_4193 {
    private static class Node {
        int x;
        int y;
        public Node(int x,int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static int n,endX,endY,result;
    private static int[][] arr;
    private static boolean[][] visited;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());


        for(int tc = 1; tc <= t; tc++){
            n = Integer.parseInt(br.readLine());
            result = 0;
            arr = new int[n][n];
            visited = new boolean[n][n];

            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            endX = Integer.parseInt(st.nextToken());
            endY = Integer.parseInt(st.nextToken());

            bfs(startX,startY);

            System.out.println("#" + tc + " " + result);
        }//testCase end
    }//main end

    private static void bfs(int x,int y){
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(x,y));
        visited[x][y] = true;

        int time = 0;
        while(!que.isEmpty()){
            int size = que.size();

            while(size --> 0){
                Node now = que.poll();
                int nowX = now.x;
                int nowY = now.y;

                for(int d = 0; d < 4; d++){
                    int nx = nowX + dx[d];
                    int ny = nowY + dy[d];

                    if(nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny] && arr[nx][ny] != 1){
                        if(arr[nx][ny] == 2){
                            if(time%3 != 2){
                                que.offer(new Node(nowX,nowY));
                                continue;
                            }
                        }
                        if(nx == endX && ny == endY){
                            result = time+1;
                            return;
                        }
                        visited[nx][ny] = true;
                        que.offer(new Node(nx,ny));
                    }
                }
            }
            time++;
        }
        result = -1;
    }//bfs end
}//class end
